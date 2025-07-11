package com.easyhooon.pokedex.feature.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.easyhooon.pokedex.core.designsystem.ComponentPreview
import com.easyhooon.pokedex.core.designsystem.theme.PokedexTheme
import com.easyhooon.pokedex.core.designsystem.theme.Small14_Mid
import com.easyhooon.pokedex.feature.main.MainTab
import com.easyhooon.pokedex.screens.FavoritesScreen
import com.easyhooon.pokedex.screens.ListScreen
import com.slack.circuit.backstack.SaveableBackStack
import com.slack.circuit.runtime.Navigator
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun MainBottomBar(
    navigator: Navigator,
    backStack: SaveableBackStack,
    modifier: Modifier = Modifier,
) {
    val visible = shouldShowBottomBar(backStack)
    val tabs = MainTab.entries.toImmutableList()

    if (visible) {
        MainBottomBarContent(
            tabs = tabs,
            backStack = backStack,
            navigator = navigator,
            modifier = modifier,
        )
    }
}

@Composable
private fun MainBottomBarContent(
    tabs: ImmutableList<MainTab>,
    backStack: SaveableBackStack,
    navigator: Navigator,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        HorizontalDivider(color = MaterialTheme.colorScheme.outline)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .windowInsetsPadding(WindowInsets.safeContent.only(WindowInsetsSides.Bottom))
                .height(64.dp)
        ) {
            tabs.forEach { tab ->
                val selected = tab.screen == backStack.topRecord?.screen
                BottomTabItem(
                    tab = tab,
                    selected = selected,
                    onClick = {
                        navigator.resetRoot(tab.screen, saveState = true, restoreState = true)
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun BottomTabItem(
    tab: MainTab,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .testTag("bottom_tab_${tab.label}")
            .semantics { this.selected = selected }
            .height(64.dp)
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 8.dp),
    ) {
        Icon(
            imageVector = if (selected) ImageVector.vectorResource(tab.selectedIconResId)
            else ImageVector.vectorResource(tab.iconResId),
            contentDescription = tab.contentDescription,
            tint = Color.Unspecified,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = tab.label,
            color = if (selected) Color(0xFF1F1F1F) else Color(0xFF9E9E9E),
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            style = Small14_Mid,
            textAlign = TextAlign.Center,
            maxLines = 1,
        )
    }
}

@ComponentPreview
@Composable
private fun MainBottomBarPreview() {
    PokedexTheme {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.safeContent.only(WindowInsetsSides.Bottom))
                .background(MaterialTheme.colorScheme.surface)
        ) {
            MainTab.entries.forEach { tab ->
                BottomTabItem(
                    tab = tab,
                    selected = tab == MainTab.LIST,
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun shouldShowBottomBar(backStack: SaveableBackStack): Boolean {
    val currentScreen = backStack.topRecord?.screen
    return currentScreen is ListScreen || currentScreen is FavoritesScreen
}
