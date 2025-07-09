package com.easyhooon.pokedex.feature.favorites_detail

import com.easyhooon.pokedex.core.model.PokemonDetailModel
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState

data class FavoritesDetailUiState(
    val isLoading: Boolean = false,
    val pokemon: PokemonDetailModel = PokemonDetailModel(),
    val eventSink: (FavoritesDetailUiEvent) -> Unit,
) : CircuitUiState

sealed interface FavoritesDetailUiEvent : CircuitUiEvent {
    data object OnBackClick : FavoritesDetailUiEvent
    data object OnFavoritesButtonClick : FavoritesDetailUiEvent
}
