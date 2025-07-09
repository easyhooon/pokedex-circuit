package com.easyhooon.pokedex.feature.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easyhooon.pokedex.core.designsystem.DevicePreview
import com.easyhooon.pokedex.core.designsystem.component.LoadingWheel
import com.easyhooon.pokedex.core.designsystem.component.PokedexTopAppBar
import com.easyhooon.pokedex.core.designsystem.component.TopAppBarNavigationType
import com.easyhooon.pokedex.core.designsystem.theme.PokedexTheme
import com.easyhooon.pokedex.feature.favorites.component.FavoritesPokemonItem
import com.easyhooon.pokedex.screens.FavoritesScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.hilt.android.components.ActivityRetainedComponent
import com.easyhooon.pokedex.core.designsystem.R as designR

@CircuitInject(FavoritesScreen::class, ActivityRetainedComponent::class)
@Composable
internal fun Favorites(
    state: FavoritesUiState,
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
                title = stringResource(R.string.favorites_title),
                navigationIconRes = designR.drawable.ic_arrow_back_gray,
                containerColor = Color.Transparent,
            )

            FavoritesContent(state = state)
        }

        if (state.isLoading) {
            LoadingWheel(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
internal fun FavoritesContent(
    state: FavoritesUiState,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier.padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            count = state.favoritesPokemonList.size,
            key = { index -> state.favoritesPokemonList[index].id },
        ) { index ->
            val pokemon = state.favoritesPokemonList[index]
            FavoritesPokemonItem(
                pokemon = pokemon,
                onItemClick = {
                    state.eventSink(FavoritesUiEvent.OnPokemonItemClick(pokemon))
                },
            )
        }
    }
}

@DevicePreview
@Composable
private fun FavoritesPreview() {
    PokedexTheme {
        Favorites(
            state = FavoritesUiState(eventSink = {}),
        )
    }
}
