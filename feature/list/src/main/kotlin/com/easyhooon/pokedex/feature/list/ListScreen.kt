package com.easyhooon.pokedex.feature.list

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.easyhooon.pokedex.core.common.ObserveAsEvents
import com.easyhooon.pokedex.core.designsystem.DevicePreview
import com.easyhooon.pokedex.core.designsystem.component.PokedexTopAppBar
import com.easyhooon.pokedex.core.designsystem.component.TopAppBarNavigationType
import com.easyhooon.pokedex.core.designsystem.theme.PokedexTheme
import com.easyhooon.pokedex.core.model.PokemonModel
import com.easyhooon.pokedex.feature.list.component.LoadErrorScreen
import com.easyhooon.pokedex.feature.list.component.LoadStateFooter
import com.easyhooon.pokedex.feature.list.component.LoadingScreen
import com.easyhooon.pokedex.feature.list.component.PokemonItem
import com.easyhooon.pokedex.feature.list.viewmodel.ListUiAction
import com.easyhooon.pokedex.feature.list.viewmodel.ListUiEvent
import com.easyhooon.pokedex.feature.list.viewmodel.ListViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import com.easyhooon.pokedex.core.designsystem.R as designR

@Composable
internal fun ListRoute(
    padding: PaddingValues,
    navigateToListDetail: (String) -> Unit,
    viewModel: ListViewModel = hiltViewModel(),
) {
    val pokemonList = viewModel.pokemonList.collectAsLazyPagingItems()
    val context = LocalContext.current

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is ListUiEvent.NavigateToListDetail -> navigateToListDetail(event.name)
            is ListUiEvent.ShowToast -> Toast.makeText(context, event.message.asString(context), Toast.LENGTH_SHORT).show()
            is ListUiEvent.RefetchPokemonList -> pokemonList.retry()
        }
    }

    ListScreen(
        padding = padding,
        pokemonList = pokemonList,
        onAction = viewModel::onAction,
    )
}

@Composable
internal fun ListScreen(
    padding: PaddingValues,
    pokemonList: LazyPagingItems<PokemonModel>,
    onAction: (ListUiAction) -> Unit,
) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding),
        ) {
            PokedexTopAppBar(
                navigationType = TopAppBarNavigationType.None,
                title = stringResource(R.string.list_title),
                navigationIconRes = designR.drawable.ic_arrow_back_gray,
                containerColor = Color.Transparent,
            )

            ListContent(
                pokemonList = pokemonList,
                onAction = onAction,
            )
        }
    }
}

@Composable
internal fun ListContent(
    pokemonList: LazyPagingItems<PokemonModel>,
    onAction: (ListUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val isLoading = pokemonList.loadState.refresh is LoadState.Loading
    val isError = pokemonList.loadState.refresh is LoadState.Error

    when {
        isLoading -> {
            LoadingScreen()
        }

        isError -> {
            LoadErrorScreen(
                onRetryButtonClick = {
                    onAction(ListUiAction.OnRetryButtonClick)
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
                count = pokemonList.itemCount,
                key = { index -> "${index}_${pokemonList[index].hashCode()}" },
            ) { index ->
                val pokemon = pokemonList[index]
                if (pokemon != null) {
                    PokemonItem(
                        pokemon = pokemon,
                        onItemClick = { onAction(ListUiAction.OnItemClick(pokemon.name)) },
                    )
                }
            }

            item(
                span = { GridItemSpan(maxLineSpan) },
            ) {
                LoadStateFooter(
                    loadState = pokemonList.loadState.append,
                    onRetryClick = { pokemonList.retry() },
                )
            }
        }
    }
}

@DevicePreview
@Composable
private fun ListScreenPreview() {
    val mockPokemonList = mutableListOf<PokemonModel>()

    for (i in 1..5) {
        mockPokemonList.add(
            PokemonModel(
                name = ("Pokemon $i"),
                url = "https://pokeapi.co/api/v2/pokemon/$i/",
            ),
        )
    }

    val mockPokemonListPagingItems = MutableStateFlow(PagingData.from(mockPokemonList)).collectAsLazyPagingItems()

    PokedexTheme {
        ListScreen(
            padding = PaddingValues(),
            pokemonList = mockPokemonListPagingItems,
            onAction = {},
        )
    }
}
