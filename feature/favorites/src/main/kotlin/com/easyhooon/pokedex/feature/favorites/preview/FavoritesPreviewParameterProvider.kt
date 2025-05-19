package com.easyhooon.pokedex.feature.favorites.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.easyhooon.pokedex.feature.favorites.viewmodel.FavoritesUiState

internal class FavoritesPreviewParameterProvider : PreviewParameterProvider<FavoritesUiState> {
    override val values = sequenceOf(
        FavoritesUiState(),
    )
}
