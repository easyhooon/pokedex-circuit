package com.easyhooon.pokedex.feature.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.map
import com.easyhooon.pokedex.core.data.api.repository.PokemonRepository
import com.easyhooon.pokedex.screens.ListDetailScreen
import com.easyhooon.pokedex.screens.ListScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import timber.log.Timber

class ListPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
    private val repository: PokemonRepository,
) : Presenter<ListUiState> {

    @Composable
    override fun present(): ListUiState {
        Timber.d("## ListPresenter present()")
        val scope = rememberCoroutineScope()
        val basePaging = rememberRetained {
            repository.getPokemonList().cachedIn(scope)
        }
        val favorites = rememberRetained {
            repository.getFavoritesPokemonList()
                .map { favorites -> favorites.map { pokemon -> pokemon.id } }
        }

        val pagingWithFavorite = remember(basePaging, favorites) {
            combine(basePaging, favorites) { pagingData, favoriteIds ->
                pagingData.map { pokemon ->
                    pokemon.copy(isFavorite = favoriteIds.contains(pokemon.getId()))
                }
            }
        }.collectAsLazyPagingItems()

        return ListUiState(
            pokemonList = pagingWithFavorite,
        ) { event ->
            when (event) {
                is ListUiEvent.OnRetryButtonClick -> pagingWithFavorite.retry()
                is ListUiEvent.OnPokemonItemClick -> {
                    navigator.goTo(ListDetailScreen(pokemonName = event.pokemonName))
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
