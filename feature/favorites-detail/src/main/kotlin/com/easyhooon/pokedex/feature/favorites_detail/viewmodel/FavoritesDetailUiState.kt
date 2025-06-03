package com.easyhooon.pokedex.feature.favorites_detail.viewmodel

import com.easyhooon.pokedex.core.model.PokemonDetailModel

data class FavoritesDetailUiState(
    val isLoading: Boolean = false,
    val pokemon: PokemonDetailModel = PokemonDetailModel(),
)
