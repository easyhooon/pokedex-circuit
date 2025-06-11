package com.easyhooon.pokedex.feature.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.easyhooon.pokedex.core.designsystem.DevicePreview
import com.easyhooon.pokedex.core.designsystem.component.PokedexTopAppBar
import com.easyhooon.pokedex.core.designsystem.component.TopAppBarNavigationType
import com.easyhooon.pokedex.core.designsystem.theme.PokedexTheme
import com.easyhooon.pokedex.core.model.PokemonModel
import com.easyhooon.pokedex.feature.list.component.LoadErrorScreen
import com.easyhooon.pokedex.feature.list.component.LoadStateFooter
import com.easyhooon.pokedex.feature.list.component.LoadingScreen
import com.easyhooon.pokedex.feature.list.component.PokemonItem
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.parcelize.Parcelize
import com.easyhooon.pokedex.core.designsystem.R as designR

@Parcelize
data object ListScreen : Screen {
    data class State(
        val pokemonList: LazyPagingItems<PokemonModel>,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data object OnRetryButtonClick : Event
        data class OnPokemonItemClick(val name: String) : Event
    }
}

@Composable
fun List(
    state: ListScreen.State,
    modifier: Modifier = Modifier,
) {
    Box {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            PokedexTopAppBar(
                navigationType = TopAppBarNavigationType.None,
                title = stringResource(R.string.list_title),
                navigationIconRes = designR.drawable.ic_arrow_back_gray,
                containerColor = Color.Transparent,
            )

            ListContent(state = state)
        }
    }
}

@Composable
internal fun ListContent(
    state: ListScreen.State,
    modifier: Modifier = Modifier,
) {
    val isLoading = state.pokemonList.loadState.refresh is LoadState.Loading
    val isError = state.pokemonList.loadState.refresh is LoadState.Error

    when {
        isLoading -> {
            LoadingScreen()
        }

        isError -> {
            LoadErrorScreen(
                onRetryButtonClick = {
                    state.eventSink(ListScreen.Event.OnRetryButtonClick)
                },
            )
        }

        else -> LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                count = state.pokemonList.itemCount,
                key = { index -> "${index}_${state.pokemonList[index].hashCode()}" },
            ) { index ->
                val pokemon = state.pokemonList[index]
                if (pokemon != null) {
                    PokemonItem(
                        pokemon = pokemon,
                        onPokemonItemClick = {
                            state.eventSink(ListScreen.Event.OnPokemonItemClick(pokemon.name))
                        },
                    )
                }
            }

            item(
                span = { GridItemSpan(maxLineSpan) },
            ) {
                LoadStateFooter(
                    loadState = state.pokemonList.loadState.append,
                    onRetryClick = {
                        state.eventSink(ListScreen.Event.OnRetryButtonClick)
                    },
                )
            }
        }
    }
}

@DevicePreview
@Composable
private fun ListPreview() {
    val mockPokemonList = mutableListOf<PokemonModel>()

    for (i in 1..5) {
        mockPokemonList.add(
            PokemonModel(
                name = ("Pokemon $i"),
                url = "https://pokeapi.co/api/v2/pokemon/$i/",
            ),
        )
    }

    val mockPokemonListPagingItems =
        MutableStateFlow(PagingData.from(mockPokemonList)).collectAsLazyPagingItems()

    PokedexTheme {
        List(
            state = ListScreen.State(
                pokemonList = mockPokemonListPagingItems,
                eventSink = {},
            ),
        )
    }
}
