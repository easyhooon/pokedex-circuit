package com.easyhooon.pokedex.feature.main.bottomnavigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.easyhooon.pokedex.core.designsystem.component.PokedexScaffold
import com.easyhooon.pokedex.feature.main.component.MainBottomBar
import com.easyhooon.pokedex.feature.main.component.MainTab
import com.easyhooon.pokedex.screens.BottomNavigationScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.foundation.NavigableCircuitContent
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.collections.immutable.toImmutableList

@CircuitInject(BottomNavigationScreen::class, ActivityRetainedComponent::class)
@Composable
fun BottomNavigation(
    state: BottomNavigationUiState,
    modifier: Modifier = Modifier,
) {
    PokedexScaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            MainBottomBar(
                tabs = MainTab.entries.toImmutableList(),
                currentTab = state.currentTab,
                onTabSelected = { tab ->
                    state.eventSink(BottomNavigationUiEvent.OnTabSelected(tab))
                },
            )
        },
    ) { innerPadding ->
        NavigableCircuitContent(
            navigator = state.navigator,
            backStack = state.backStack,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        )
    }
}
