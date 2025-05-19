package com.easyhooon.pokedex.feature.list.viewmodel

import com.easyhooon.pokedex.core.common.UiText

sealed interface ListUiEvent {
    data class NavigateToListDetail(val name: String) : ListUiEvent
    data class ShowToast(val message: UiText) : ListUiEvent
    data object RefetchPokemonList : ListUiEvent
}
