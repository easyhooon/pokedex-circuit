package com.easyhooon.pokedex.feature.list.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.easyhooon.pokedex.core.navigation.MainTabRoute
import com.easyhooon.pokedex.feature.list.ListRoute

fun NavController.navigateToList(navOptions: NavOptions) {
    navigate(MainTabRoute.List, navOptions)
}

fun NavGraphBuilder.listNavGraph(
    padding: PaddingValues,
    navigateToListDetail: (String) -> Unit,
) {
    composable<MainTabRoute.List> {
        ListRoute(
            padding = padding,
            navigateToListDetail = navigateToListDetail,
        )
    }
}
