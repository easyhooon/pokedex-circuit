package com.easyhooon.pokedex.feature.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import com.easyhooon.pokedex.core.data.api.repository.PokemonRepository
import com.easyhooon.pokedex.feature.list_detail.ListDetailScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

class ListPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
    private val repository: PokemonRepository,
) : Presenter<ListScreen.State> {

    @Composable
    override fun present(): ListScreen.State {
        val scope = rememberCoroutineScope()
        val pokemonList = repository.getPokemonList().cachedIn(scope).collectAsLazyPagingItems()

        return ListScreen.State(
            pokemonList = pokemonList,
        ) { event ->
            when (event) {
                is ListScreen.Event.OnRetryButtonClick -> pokemonList.retry()
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
