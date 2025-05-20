package com.easyhooon.pokedex.list_detail

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.easyhooon.pokedex.core.common.InsertFavoriteResult
import com.easyhooon.pokedex.core.data.api.repository.PokemonRepository
import com.easyhooon.pokedex.core.model.PokemonDetailModel
import com.easyhooon.pokedex.feature.list_detail.R
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

class ListDetailPresenter @AssistedInject constructor(
    @Assisted private val screen: ListDetailScreen,
    @Assisted private val navigator: Navigator,
    private val repository: PokemonRepository,
    @ApplicationContext private val context: Context,
) : Presenter<ListDetailScreen.State> {

    @Composable
    override fun present(): ListDetailScreen.State {
        val scope = rememberCoroutineScope()
        var isLoading by rememberRetained { mutableStateOf(false) }
        var name by rememberRetained { mutableStateOf("") }
        var pokemon by rememberRetained { mutableStateOf(PokemonDetailModel()) }
        var isNetworkErrorDialogVisible by rememberRetained { mutableStateOf(false) }
        var isServerErrorDialogVisible by rememberRetained { mutableStateOf(false) }

        fun getPokemonDetail(name: String) {
            scope.launch {
                isLoading = true
                repository.getPokemonDetail(name)
                    .onSuccess { pokemonDetail ->
                        pokemon = pokemonDetail
                    }
                    .onFailure { throwable ->
                        Timber.e(throwable)
                        // handleException(throwable, this)
                    }
                isLoading = false
            }
        }

        fun addFavoritePokemon(pokemon: PokemonDetailModel) {
            scope.launch {
                isLoading = true
                when (repository.insertFavoritePokemon(pokemon)) {
                    is InsertFavoriteResult.Success -> {
                        Toast.makeText(
                            context,
                            context.getString(R.string.favorites_add_success),
                            Toast.LENGTH_SHORT,
                        ).show()
                    }

                    is InsertFavoriteResult.Duplicate -> {
                        Toast.makeText(
                            context,
                            context.getString(R.string.favorites_add_success),
                            Toast.LENGTH_SHORT,
                        ).show()
                    }

                    is InsertFavoriteResult.LimitExceeded -> {
                        Toast.makeText(
                            context,
                            context.getString(R.string.favorites_add_success),
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
                isLoading = false
            }
        }

        fun setServerErrorDialogVisible(flag: Boolean) {
            isServerErrorDialogVisible = flag
        }

        fun setNetworkErrorDialogVisible(flag: Boolean) {
            isNetworkErrorDialogVisible = flag
        }

        fun refresh(error: ErrorType) {
            getPokemonDetail(name)

            when (error) {
                ErrorType.NETWORK -> setNetworkErrorDialogVisible(false)
                ErrorType.SERVER -> setServerErrorDialogVisible(false)
            }
        }

        LaunchedEffect(screen.name) {
            getPokemonDetail(screen.name)
        }

        return ListDetailScreen.State(
            isLoading = isLoading,
            name = screen.name,
            pokemon = pokemon,
            isNetworkErrorDialogVisible = isNetworkErrorDialogVisible,
            isServerErrorDialogVisible = isServerErrorDialogVisible,
        ) { event ->
            when (event) {
                is ListDetailScreen.Event.OnBackClick -> navigator.pop()
                is ListDetailScreen.Event.OnFavoritesButtonClick -> addFavoritePokemon(pokemon)
                is ListDetailScreen.Event.OnRetryButtonClick -> {}
            }
        }
    }

    @CircuitInject(ListDetailScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(
            screen: ListDetailScreen,
            navigator: Navigator,
        ): ListDetailPresenter
    }

//    override fun setServerErrorDialogVisible(flag: Boolean) {
//        isServerErrorDialogVisible = flag
//    }
//
//    override fun setNetworkErrorDialogVisible(flag: Boolean) {
//        isNetworkErrorDialogVisible = flag
//    }
}
