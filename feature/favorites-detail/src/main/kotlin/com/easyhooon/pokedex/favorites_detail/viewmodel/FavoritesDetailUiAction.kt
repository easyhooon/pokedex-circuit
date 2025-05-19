package com.easyhooon.pokedex.favorites_detail.viewmodel

import com.easyhooon.pokedex.core.model.PokemonDetailModel

sealed interface FavoritesDetailUiAction {
    data object OnBackClick : FavoritesDetailUiAction
    data class OnFavoritesButtonClick(val pokemon: PokemonDetailModel) : FavoritesDetailUiAction
}
