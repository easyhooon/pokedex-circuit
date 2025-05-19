package com.easyhooon.pokedex.feature.favorites.viewmodel

import com.easyhooon.pokedex.core.model.PokemonDetailModel

data class FavoritesUiState(
    val isLoading: Boolean = false,
    val pokemon: PokemonDetailModel = PokemonDetailModel(),
    val isServerErrorDialogVisible: Boolean = false,
    val isNetworkErrorDialogVisible: Boolean = false,
)
