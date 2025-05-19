package com.easyhooon.pokedex.feature.main

import androidx.compose.runtime.Composable
import com.easyhooon.pokedex.core.navigation.MainTabRoute
import com.easyhooon.pokedex.core.navigation.Route
import com.easyhooon.pokedex.core.designsystem.R as designR

internal enum class MainTab(
    val iconResId: Int,
    val selectedIconResId: Int,
    internal val contentDescription: String,
    val label: String,
    val route: MainTabRoute,
) {
    LIST(
        iconResId = R.drawable.ic_list,
        selectedIconResId = R.drawable.ic_selected_list,
        contentDescription = "List Icon",
        label = "목록",
        route = MainTabRoute.List,
    ),
    FAVORITES(
        iconResId = R.drawable.ic_favorites,
        selectedIconResId = designR.drawable.ic_selected_favorites,
        contentDescription = "Favorites Icon",
        label = "즐겨찾기",
        route = MainTabRoute.Favorites,
    ),
    ;

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}
