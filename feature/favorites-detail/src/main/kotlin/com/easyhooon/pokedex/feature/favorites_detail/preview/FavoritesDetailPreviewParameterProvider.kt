package com.easyhooon.pokedex.feature.favorites_detail.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.easyhooon.pokedex.feature.favorites_detail.viewmodel.FavoritesDetailUiState

internal class FavoritesDetailPreviewParameterProvider : PreviewParameterProvider<FavoritesDetailUiState> {
    override val values = sequenceOf(
        FavoritesDetailUiState(),
    )
}
