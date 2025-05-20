package com.easyhooon.pokedex.feature.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.easyhooon.pokedex.core.data.api.repository.PokemonRepository
import com.easyhooon.pokedex.favorites_detail.FavoritesDetailScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.collections.immutable.toImmutableList

class FavoritesPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
    private val repository: PokemonRepository,
) : Presenter<FavoritesScreen.State> {
    @Composable
    override fun present(): FavoritesScreen.State {
        val isLoading by rememberRetained { mutableStateOf(false) }
        val favoritesPokemonList by repository.getFavoritesPokemonList().collectAsState(emptyList())

        return FavoritesScreen.State(
            isLoading = isLoading,
            favoritesPokemonList = favoritesPokemonList.toImmutableList(),
        ) { event ->
            when (event) {
                is FavoritesScreen.Event.OnPokemonItemClick -> {
                    navigator.goTo(FavoritesDetailScreen(pokemon = event.pokemon))
                }
            }
        }
    }

    @CircuitInject(FavoritesScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): FavoritesPresenter
    }
}
