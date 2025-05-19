package com.easyhooon.pokedex.list_detail.viewmodel

import com.easyhooon.pokedex.core.common.UiText

sealed interface ListDetailUiEvent {
    data object NavigateBack : ListDetailUiEvent
    data class ShowToast(val message: UiText) : ListDetailUiEvent
}
