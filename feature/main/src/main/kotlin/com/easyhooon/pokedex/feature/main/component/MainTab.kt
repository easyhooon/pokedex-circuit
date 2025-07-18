package com.easyhooon.pokedex.feature.main.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.easyhooon.pokedex.feature.main.R
import com.easyhooon.pokedex.screens.FavoritesScreen
import com.easyhooon.pokedex.screens.ListScreen
import com.slack.circuit.runtime.screen.Screen
import com.easyhooon.pokedex.core.designsystem.R as designR

enum class MainTab(
    @DrawableRes val iconResId: Int,
    @DrawableRes val selectedIconResId: Int,
    @StringRes val labelResId: Int,
    internal val contentDescription: String,
    val screen: Screen,
) {
    LIST(
        iconResId = R.drawable.ic_list,
        selectedIconResId = R.drawable.ic_selected_list,
        contentDescription = "List Icon",
        labelResId = R.string.list,
        screen = ListScreen,
    ),
    FAVORITES(
        iconResId = R.drawable.ic_favorites,
        selectedIconResId = designR.drawable.ic_selected_favorites,
        contentDescription = "Favorites Icon",
        labelResId = R.string.favorites,
        screen = FavoritesScreen,
    ),
}
