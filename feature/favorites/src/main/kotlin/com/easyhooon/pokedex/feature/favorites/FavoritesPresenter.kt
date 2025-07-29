package com.easyhooon.pokedex.feature.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.easyhooon.pokedex.core.data.api.repository.PokemonRepository
import com.easyhooon.pokedex.screens.FavoritesDetailScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.retained.collectAsRetainedState
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.collections.immutable.toImmutableList
import com.easyhooon.pokedex.screens.FavoritesScreen
import timber.log.Timber

class FavoritesPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
    private val repository: PokemonRepository,
) : Presenter<FavoritesUiState> {
    @Composable
    override fun present(): FavoritesUiState {
        Timber.d("## FavoritesPresenter present()")
        val isLoading by rememberRetained { mutableStateOf(false) }
        val favoritesPokemonList by repository.getFavoritesPokemonList().collectAsRetainedState(emptyList())

        return FavoritesUiState(
            isLoading = isLoading,
            favoritesPokemonList = favoritesPokemonList.toImmutableList(),
        ) { event ->
            when (event) {
                is FavoritesUiEvent.OnPokemonItemClick -> {
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
