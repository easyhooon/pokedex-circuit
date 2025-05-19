package com.easyhooon.pokedex.favorites_detail.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.easyhooon.pokedex.favorites_detail.viewmodel.FavoritesDetailUiState

internal class FavoritesDetailPreviewParameterProvider : PreviewParameterProvider<FavoritesDetailUiState> {
    override val values = sequenceOf(
        FavoritesDetailUiState(),
    )
}
