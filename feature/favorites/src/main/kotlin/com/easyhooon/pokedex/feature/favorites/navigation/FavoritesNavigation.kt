package com.easyhooon.pokedex.feature.favorites.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.easyhooon.pokedex.core.model.PokemonDetailModel
import com.easyhooon.pokedex.core.navigation.MainTabRoute
import com.easyhooon.pokedex.feature.favorites.FavoritesRoute

fun NavController.navigateToFavorites(navOptions: NavOptions) {
    navigate(MainTabRoute.Favorites, navOptions)
}

fun NavGraphBuilder.favoritesNavGraph(
    padding: PaddingValues,
    navigateToFavoritesDetail: (PokemonDetailModel) -> Unit,
) {
    composable<MainTabRoute.Favorites> {
        FavoritesRoute(
            padding = padding,
            navigateToFavoritesDetail = navigateToFavoritesDetail,
        )
    }
}
