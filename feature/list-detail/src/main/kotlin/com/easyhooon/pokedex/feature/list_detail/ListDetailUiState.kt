package com.easyhooon.pokedex.feature.list_detail

import com.easyhooon.pokedex.core.model.PokemonDetailModel
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState

data class ListDetailUiState(
    val isLoading: Boolean = false,
    val pokemon: PokemonDetailModel = PokemonDetailModel(),
    val isNetworkErrorDialogVisible: Boolean = false,
    val isServerErrorDialogVisible: Boolean = false,
    val eventSink: (ListDetailUiEvent) -> Unit,
) : CircuitUiState

sealed interface ListDetailUiEvent : CircuitUiEvent {
    data object OnBackClick : ListDetailUiEvent
    data object OnFavoritesButtonClick : ListDetailUiEvent
    data class OnRetryButtonClick(val errorType: ErrorType) : ListDetailUiEvent
}

enum class ErrorType {
    NETWORK,
    SERVER,
}
