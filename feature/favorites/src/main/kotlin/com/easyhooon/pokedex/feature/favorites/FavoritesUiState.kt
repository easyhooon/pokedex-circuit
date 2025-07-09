package com.easyhooon.pokedex.feature.favorites

import com.easyhooon.pokedex.core.model.PokemonDetailModel
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class FavoritesUiState(
    val isLoading: Boolean = false,
    val favoritesPokemonList: ImmutableList<PokemonDetailModel> = persistentListOf(),
    val eventSink: (FavoritesUiEvent) -> Unit,
) : CircuitUiState

sealed interface FavoritesUiEvent : CircuitUiEvent {
    data class OnPokemonItemClick(val pokemon: PokemonDetailModel) : FavoritesUiEvent
}
