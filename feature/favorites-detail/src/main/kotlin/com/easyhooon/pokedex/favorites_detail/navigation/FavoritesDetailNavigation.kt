package com.easyhooon.pokedex.favorites_detail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.easyhooon.pokedex.core.model.PokemonDetailModel
import com.easyhooon.pokedex.core.navigation.Route
import com.easyhooon.pokedex.favorites_detail.FavoritesDetailRoute

fun NavController.navigateToFavoritesDetail(pokemon: PokemonDetailModel) {
    navigate(Route.FavoritesDetail(pokemon))
}

fun NavGraphBuilder.favoritesDetailNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
) {
    composable<Route.FavoritesDetail>(
        typeMap = Route.FavoritesDetail.typeMap,
    ) {
        FavoritesDetailRoute(
            padding = padding,
            popBackStack = popBackStack,
        )
    }
}
