package com.easyhooon.pokedex.favorites_detail

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import com.easyhooon.pokedex.core.data.api.repository.PokemonRepository
import com.easyhooon.pokedex.feature.favorites_detail.R
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import timber.log.Timber

class FavoritesDetailPresenter @AssistedInject constructor(
    @Assisted private val screen: FavoritesDetailScreen,
    @Assisted private val navigator: Navigator,
    private val repository: PokemonRepository,
    @ApplicationContext private val context: Context,
) : Presenter<FavoritesDetailScreen.State> {
    @Composable
    override fun present(): FavoritesDetailScreen.State {
        val scope = rememberCoroutineScope()
        val isLoading by rememberRetained { mutableStateOf(false) }

        @Suppress("TooGenericExceptionCaught")
        fun removeFavoritePokemon() {
            scope.launch {
                try {
                    val affectedRows = repository.deleteFavoritePokemon(screen.pokemon)
                    if (affectedRows > 0) {
                        Toast.makeText(context, context.getString(R.string.remove_success), Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, context.getString(R.string.remove_failed), Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Timber.e(e)
                    Toast.makeText(context, context.getString(R.string.remove_failed), Toast.LENGTH_SHORT).show()
                }
            }
        }

        return FavoritesDetailScreen.State(
            isLoading = isLoading,
            pokemon = screen.pokemon,
        ) { event ->
            when (event) {
                is FavoritesDetailScreen.Event.OnBackClick -> navigator.pop()
                is FavoritesDetailScreen.Event.OnFavoritesButtonClick -> removeFavoritePokemon()
            }
        }
    }

    @CircuitInject(FavoritesDetailScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(
            screen: FavoritesDetailScreen,
            navigator: Navigator,
        ): FavoritesDetailPresenter
    }
}
