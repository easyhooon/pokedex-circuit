package com.easyhooon.pokedex.feature.favorites_detail.viewmodel

import com.easyhooon.pokedex.core.common.UiText

sealed interface FavoritesDetailUiEvent {
    data object NavigateBack : FavoritesDetailUiEvent
    data class ShowToast(val message: UiText) : FavoritesDetailUiEvent
}
