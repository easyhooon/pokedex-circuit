package com.easyhooon.pokedex.di.factory

import com.easyhooon.pokedex.feature.favorites.FavoritesPresenter
import com.easyhooon.pokedex.feature.favorites.FavoritesScreen
import com.easyhooon.pokedex.feature.favorites_detail.FavoritesDetailPresenter
import com.easyhooon.pokedex.feature.favorites_detail.FavoritesDetailScreen
import com.easyhooon.pokedex.feature.list.ListPresenter
import com.easyhooon.pokedex.feature.list.ListScreen
import com.easyhooon.pokedex.feature.list_detail.ListDetailPresenter
import com.easyhooon.pokedex.feature.list_detail.ListDetailScreen
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen

class CircuitPresenterFactory : Presenter.Factory {
    override fun create(
        screen: Screen,
        navigator: Navigator,
        context: CircuitContext
    ): Presenter<*> {
        return when (screen) {
            is ListScreen -> ListPresenter(screen, navigator)
            is ListDetailScreen -> ListDetailPresenter(screen, navigator)
            is FavoritesScreen -> FavoritesPresenter(screen, navigator)
            is FavoritesDetailScreen -> FavoritesDetailPresenter(screen, navigator)
            else -> throw Exception("Invalid Screen Detected! :: $screen")
        }
    }
}
