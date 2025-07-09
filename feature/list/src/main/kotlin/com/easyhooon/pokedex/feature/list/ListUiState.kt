package com.easyhooon.pokedex.feature.list

import androidx.paging.compose.LazyPagingItems
import com.easyhooon.pokedex.core.model.PokemonModel
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState

data class ListUiState(
    val pokemonList: LazyPagingItems<PokemonModel>,
    val eventSink: (ListUiEvent) -> Unit,
) : CircuitUiState

sealed interface ListUiEvent : CircuitUiEvent {
    data object OnRetryButtonClick : ListUiEvent
    data class OnPokemonItemClick(val pokemonName: String) : ListUiEvent
}
