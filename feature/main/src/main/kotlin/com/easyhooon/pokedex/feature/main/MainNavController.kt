package com.easyhooon.pokedex.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.easyhooon.pokedex.core.model.PokemonDetailModel
import com.easyhooon.pokedex.core.navigation.MainTabRoute
import com.easyhooon.pokedex.core.navigation.Route
import com.easyhooon.pokedex.favorites_detail.navigation.navigateToFavoritesDetail
import com.easyhooon.pokedex.feature.favorites.navigation.navigateToFavorites
import com.easyhooon.pokedex.feature.list.navigation.navigateToList
import com.easyhooon.pokedex.list_detail.navigation.navigateToListDetail

internal class MainNavController(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = MainTab.LIST.route

    val currentTab: MainTab?
        @Composable get() = MainTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.LIST -> navController.navigateToList(navOptions)
            MainTab.FAVORITES -> navController.navigateToFavorites(navOptions)
        }
    }

    fun navigateToListDetail(name: String) {
        navController.navigateToListDetail(name)
    }

    fun navigateToFavoritesDetail(pokemon: PokemonDetailModel) {
        navController.navigateToFavoritesDetail(pokemon)
    }

    private fun popBackStack() {
        navController.popBackStack()
    }

    // https://github.com/droidknights/DroidKnights2023_App/pull/243/commits/4bfb6d13908eaaab38ab3a59747d628efa3893cb
    fun popBackStackIfNotList() {
        if (!isSameCurrentDestination<MainTabRoute.List>()) {
            popBackStack()
        }
    }

    fun clearBackStack() {
        val options = NavOptions.Builder()
            .setPopUpTo(navController.graph.findStartDestination().id, inclusive = false)
            .build()
        navController.navigate(startDestination, options)
    }

    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean {
        return navController.currentDestination?.hasRoute<T>() == true
    }

    @Composable
    fun shouldShowBottomBar() = MainTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }
}

@Composable
internal fun rememberMainNavController(
    navController: NavHostController = rememberNavController(),
): MainNavController = remember(navController) {
    MainNavController(navController)
}
