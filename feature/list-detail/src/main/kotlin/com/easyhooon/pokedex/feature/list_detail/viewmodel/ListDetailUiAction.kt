package com.easyhooon.pokedex.feature.list_detail.viewmodel

import com.easyhooon.pokedex.core.model.PokemonDetailModel

sealed interface ListDetailUiAction {
    data object OnBackClick : ListDetailUiAction
    data class OnButtonClick(val pokemon: PokemonDetailModel) : ListDetailUiAction
    data class OnRetryClick(val error: ErrorType) : ListDetailUiAction
}

enum class ErrorType {
    NETWORK,
    SERVER,
}
