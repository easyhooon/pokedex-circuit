package com.easyhooon.pokedex.feature.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.easyhooon.pokedex.core.data.api.repository.PokemonRepository
import com.easyhooon.pokedex.feature.favorites_detail.FavoritesDetailScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.retained.collectAsRetainedState
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.collections.immutable.toImmutableList

class FavoritesPresenter(
    private val navigator: Navigator,
    private val repository: PokemonRepository,
) : Presenter<FavoritesScreen.State> {
    @Composable
    override fun present(): FavoritesScreen.State {
        val isLoading by rememberRetained { mutableStateOf(false) }
        val favoritesPokemonList by repository.getFavoritesPokemonList().collectAsRetainedState(emptyList())

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
}
