package com.easyhooon.pokedex.feature.list_detail.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.easyhooon.pokedex.feature.list_detail.viewmodel.ListDetailUiState

internal class ListDetailPreviewParameterProvider : PreviewParameterProvider<ListDetailUiState> {
    override val values = sequenceOf(
        ListDetailUiState(),
    )
}
