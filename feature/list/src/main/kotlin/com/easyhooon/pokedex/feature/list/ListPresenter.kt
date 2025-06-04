package com.easyhooon.pokedex.feature.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.map
import com.easyhooon.pokedex.core.data.api.repository.PokemonRepository
import com.easyhooon.pokedex.feature.list_detail.ListDetailScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class ListPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
    private val repository: PokemonRepository,
) : Presenter<ListScreen.State> {

    @Composable
    override fun present(): ListScreen.State {
        val scope = rememberCoroutineScope()
        val basePaging = repository.getPokemonList().cachedIn(scope)
        val favorites = repository.getFavoritesPokemonList()
            .map { favorites -> favorites.map { pokemon -> pokemon.id } }

        val pagingWithFavorite = androidx.compose.runtime.remember(basePaging, favorites) {
            combine(basePaging, favorites) { pagingData, favoriteIds ->
                pagingData.map { pokemon ->
                    pokemon.copy(isFavorite = favoriteIds.contains(pokemon.getId()))
                }
            }
        }.collectAsLazyPagingItems()

        return ListScreen.State(
            pokemonList = pagingWithFavorite,
        ) { event ->
            when (event) {
                is ListScreen.Event.OnRetryButtonClick -> pagingWithFavorite.retry()
                is ListScreen.Event.OnPokemonItemClick -> {
                    navigator.goTo(ListDetailScreen(name = event.name))
                }
            }
        }
    }

    @CircuitInject(ListScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): ListPresenter
    }
}
