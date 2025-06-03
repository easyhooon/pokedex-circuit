package com.easyhooon.pokedex.feature.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhooon.pokedex.core.common.ObserveAsEvents
import com.easyhooon.pokedex.core.designsystem.DevicePreview
import com.easyhooon.pokedex.core.designsystem.component.LoadingWheel
import com.easyhooon.pokedex.core.designsystem.component.PokedexTopAppBar
import com.easyhooon.pokedex.core.designsystem.component.TopAppBarNavigationType
import com.easyhooon.pokedex.core.designsystem.theme.PokedexTheme
import com.easyhooon.pokedex.core.model.PokemonDetailModel
import com.easyhooon.pokedex.feature.favorites.component.FavoritesPokemonItem
import com.easyhooon.pokedex.feature.favorites.preview.FavoritesPreviewParameterProvider
import com.easyhooon.pokedex.feature.favorites.viewmodel.FavoritesUiAction
import com.easyhooon.pokedex.feature.favorites.viewmodel.FavoritesUiEvent
import com.easyhooon.pokedex.feature.favorites.viewmodel.FavoritesUiState
import com.easyhooon.pokedex.feature.favorites.viewmodel.FavoritesViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import com.easyhooon.pokedex.core.designsystem.R as designR
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun FavoritesRoute(
    padding: PaddingValues,
    navigateToFavoritesDetail: (PokemonDetailModel) -> Unit,
    viewModel: FavoritesViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val favoritesPokemonList by viewModel.favoritesPokemonList.collectAsStateWithLifecycle(initialValue = emptyList())

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is FavoritesUiEvent.NavigateToFavoritesDetail -> navigateToFavoritesDetail(event.pokemon)
        }
    }

    FavoritesScreen(
        padding = padding,
        uiState = uiState,
        favoritesPokemonList = favoritesPokemonList.toImmutableList(),
        onAction = viewModel::onHomeUiAction,
    )
}

@Composable
internal fun FavoritesScreen(
    padding: PaddingValues,
    uiState: FavoritesUiState,
    favoritesPokemonList: ImmutableList<PokemonDetailModel>,
    onAction: (FavoritesUiAction) -> Unit,
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
                title = stringResource(R.string.favorites_title),
                navigationIconRes = designR.drawable.ic_arrow_back_gray,
                containerColor = Color.Transparent,
            )

            FavoritesContent(
                favoritesPokemonList = favoritesPokemonList,
                onAction = onAction,
            )
        }

        if (uiState.isLoading) {
            LoadingWheel(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
internal fun FavoritesContent(
    favoritesPokemonList: ImmutableList<PokemonDetailModel>,
    onAction: (FavoritesUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier.padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            count = favoritesPokemonList.size,
            key = { index -> favoritesPokemonList[index].id },
        ) { index ->
            val pokemon = favoritesPokemonList[index]
            FavoritesPokemonItem(
                pokemon = pokemon,
                onItemClick = {
                    onAction(FavoritesUiAction.OnItemClick(pokemon))
                },
            )
        }
    }
}

@DevicePreview
@Composable
private fun FavoritesScreenPreview(
    @PreviewParameter(FavoritesPreviewParameterProvider::class)
    favoritesUiState: FavoritesUiState,
) {
    PokedexTheme {
        FavoritesScreen(
            padding = PaddingValues(),
            uiState = favoritesUiState,
            favoritesPokemonList = emptyList<PokemonDetailModel>().toImmutableList(),
            onAction = {},
        )
    }
}
