package com.easyhooon.pokedex.feature.favorites.viewmodel

import com.easyhooon.pokedex.core.model.PokemonDetailModel

sealed interface FavoritesUiEvent {
    data class NavigateToFavoritesDetail(val pokemon: PokemonDetailModel) : FavoritesUiEvent
}
