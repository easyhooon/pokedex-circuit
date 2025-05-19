package com.easyhooon.pokedex.feature.list.viewmodel

sealed interface ListUiAction {
    data class OnItemClick(val name: String) : ListUiAction
    data object OnRetryButtonClick : ListUiAction
}
