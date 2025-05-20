package com.easyhooon.pokedex.feature.main

import androidx.annotation.DrawableRes
import com.easyhooon.pokedex.feature.favorites.FavoritesScreen
import com.easyhooon.pokedex.feature.list.ListScreen
import com.slack.circuit.runtime.screen.Screen
import com.easyhooon.pokedex.core.designsystem.R as designR

internal enum class MainTab(
    @DrawableRes val iconResId: Int,
    @DrawableRes val selectedIconResId: Int,
    internal val contentDescription: String,
    val label: String,
    val screen: Screen,
) {
    LIST(
        iconResId = R.drawable.ic_list,
        selectedIconResId = R.drawable.ic_selected_list,
        contentDescription = "List Icon",
        label = "목록",
        screen = ListScreen,
    ),
    FAVORITES(
        iconResId = R.drawable.ic_favorites,
        selectedIconResId = designR.drawable.ic_selected_favorites,
        contentDescription = "Favorites Icon",
        label = "즐겨찾기",
        screen = FavoritesScreen,
    ),
}
