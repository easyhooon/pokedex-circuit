package com.easyhooon.pokedex.favorites_detail.viewmodel

import com.easyhooon.pokedex.core.common.UiText

sealed interface FavoritesDetailUiEvent {
    data object NavigateBack : FavoritesDetailUiEvent
    data class ShowToast(val message: UiText) : FavoritesDetailUiEvent
}
