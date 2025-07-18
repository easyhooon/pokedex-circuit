package com.easyhooon.pokedex.feature.main.bottomnavigation

import com.easyhooon.pokedex.feature.main.component.MainTab
import com.slack.circuit.backstack.SaveableBackStack
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator

data class BottomNavigationUiState(
    val backStack: SaveableBackStack,
    val navigator: Navigator,
    val currentTab: MainTab?,
    val eventSink: (BottomNavigationUiEvent) -> Unit,
) : CircuitUiState

sealed interface BottomNavigationUiEvent {
    data class OnTabSelected(val tab: MainTab) : BottomNavigationUiEvent
}
