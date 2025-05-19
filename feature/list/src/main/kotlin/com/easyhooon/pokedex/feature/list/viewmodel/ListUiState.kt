package com.easyhooon.pokedex.feature.list.viewmodel

data class ListUiState(
    val isLoading: Boolean = false,
    val isServerErrorDialogVisible: Boolean = false,
    val isNetworkErrorDialogVisible: Boolean = false,
)
