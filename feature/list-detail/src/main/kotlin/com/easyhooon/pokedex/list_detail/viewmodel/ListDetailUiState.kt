package com.easyhooon.pokedex.list_detail.viewmodel

import com.easyhooon.pokedex.core.model.PokemonDetailModel

data class ListDetailUiState(
    val isLoading: Boolean = false,
    val pokemon: PokemonDetailModel = PokemonDetailModel(),
    val isServerErrorDialogVisible: Boolean = false,
    val isNetworkErrorDialogVisible: Boolean = false,
)
