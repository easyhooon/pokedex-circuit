package com.easyhooon.pokedex.di.factory

import com.easyhooon.pokedex.feature.favorites.Favorites
import com.easyhooon.pokedex.feature.favorites.FavoritesScreen
import com.easyhooon.pokedex.feature.favorites_detail.FavoritesDetail
import com.easyhooon.pokedex.feature.favorites_detail.FavoritesDetailScreen
import com.easyhooon.pokedex.feature.list.ListScreen
import com.easyhooon.pokedex.feature.list.List
import com.easyhooon.pokedex.feature.list_detail.ListDetail
import com.easyhooon.pokedex.feature.list_detail.ListDetailScreen
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui

class CircuitUiFactory : Ui.Factory {
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
        return ui<CircuitUiState> { state, modifier ->
            when (state) {
                is ListScreen.State -> List(state, modifier)
                is ListDetailScreen.State -> ListDetail(state, modifier)
                is FavoritesScreen.State -> Favorites(state, modifier)
                is FavoritesDetailScreen.State -> FavoritesDetail(state, modifier)
            }
        }
    }
}
