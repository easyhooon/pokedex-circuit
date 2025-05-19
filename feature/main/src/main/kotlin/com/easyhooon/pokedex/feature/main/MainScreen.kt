package com.easyhooon.pokedex.feature.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.easyhooon.pokedex.core.designsystem.component.PokedexScaffold
import com.easyhooon.pokedex.favorites_detail.navigation.favoritesDetailNavGraph
import com.easyhooon.pokedex.feature.favorites.navigation.favoritesNavGraph
import com.easyhooon.pokedex.feature.list.navigation.listNavGraph
import com.easyhooon.pokedex.feature.main.component.MainBottomBar
import com.easyhooon.pokedex.list_detail.navigation.listDetailNavGraph
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun MainScreen(
    navigator: MainNavController = rememberMainNavController(),
) {
    PokedexScaffold(
        bottomBar = {
            MainBottomBar(
                visible = navigator.shouldShowBottomBar(),
                tabs = MainTab.entries.toImmutableList(),
                currentTab = navigator.currentTab,
                onTabSelected = {
                    navigator.navigate(it)
                },
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            modifier = Modifier.fillMaxSize(),
        ) {
            listNavGraph(
                padding = innerPadding,
                navigateToListDetail = navigator::navigateToListDetail,
            )

            listDetailNavGraph(
                padding = innerPadding,
                popBackStack = navigator::popBackStackIfNotList,
            )

            favoritesNavGraph(
                padding = innerPadding,
                navigateToFavoritesDetail = navigator::navigateToFavoritesDetail,
            )

            favoritesDetailNavGraph(
                padding = innerPadding,
                popBackStack = navigator::popBackStackIfNotList,
            )
        }
    }
}
