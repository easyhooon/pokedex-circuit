package com.easyhooon.pokedex.feature.favorites.viewmodel

import com.easyhooon.pokedex.core.model.PokemonDetailModel

sealed interface FavoritesUiAction {
    data class OnItemClick(val pokemon: PokemonDetailModel) : FavoritesUiAction
}
