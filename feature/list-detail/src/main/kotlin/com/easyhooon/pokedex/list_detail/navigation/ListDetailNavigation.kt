package com.easyhooon.pokedex.list_detail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.easyhooon.pokedex.core.navigation.Route
import com.easyhooon.pokedex.list_detail.ListDetailRoute

fun NavController.navigateToListDetail(name: String) {
    navigate(Route.ListDetail(name))
}

fun NavGraphBuilder.listDetailNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
) {
    composable<Route.ListDetail> {
        ListDetailRoute(
            padding = padding,
            popBackStack = popBackStack,
        )
    }
}
