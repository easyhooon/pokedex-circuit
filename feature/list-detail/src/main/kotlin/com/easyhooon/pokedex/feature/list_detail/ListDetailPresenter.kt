package com.easyhooon.pokedex.feature.list_detail

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.easyhooon.pokedex.core.common.ErrorHandlerActions
import com.easyhooon.pokedex.core.common.InsertFavoriteResult
import com.easyhooon.pokedex.core.common.handleException
import com.easyhooon.pokedex.core.data.api.repository.PokemonRepository
import com.easyhooon.pokedex.core.model.PokemonDetailModel
import com.easyhooon.pokedex.screens.ListDetailScreen
import com.skydoves.compose.effects.RememberedEffect
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
) : Presenter<ListDetailUiState>, ErrorHandlerActions {

    private var setServerErrorDialogVisibleCallback: ((Boolean) -> Unit)? = null
    private var setNetworkErrorDialogVisibleCallback: ((Boolean) -> Unit)? = null

    @Composable
    override fun present(): ListDetailUiState {
        val scope = rememberCoroutineScope()
        var isLoading by rememberRetained { mutableStateOf(false) }
        var pokemon by rememberRetained { mutableStateOf(PokemonDetailModel()) }
        var isNetworkErrorDialogVisible by rememberRetained { mutableStateOf(false) }
        var isServerErrorDialogVisible by rememberRetained { mutableStateOf(false) }

        setServerErrorDialogVisibleCallback = { flag ->
            isServerErrorDialogVisible = flag
        }
        setNetworkErrorDialogVisibleCallback = { flag ->
            isNetworkErrorDialogVisible = flag
        }

        fun getPokemonDetail(name: String) {
            scope.launch {
                isLoading = true
                repository.getPokemonDetail(name)
                    .onSuccess { pokemonDetail ->
                        pokemon = pokemonDetail
                    }
                    .onFailure { throwable ->
                        Timber.e(throwable)
                        handleException(throwable, this@ListDetailPresenter)
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
                            context.getString(R.string.favorites_duplicate),
                            Toast.LENGTH_SHORT,
                        ).show()
                    }

                    is InsertFavoriteResult.LimitExceeded -> {
                        Toast.makeText(
                            context,
                            context.getString(R.string.favorites_max_limit),
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
                isLoading = false
            }
        }

        fun refresh(error: ErrorType) {
            getPokemonDetail(screen.pokemonName)

            when (error) {
                ErrorType.NETWORK -> isNetworkErrorDialogVisible = false
                ErrorType.SERVER -> isServerErrorDialogVisible = false
            }
        }

        RememberedEffect(screen.pokemonName) {
            getPokemonDetail(screen.pokemonName)
        }

        return ListDetailUiState(
            isLoading = isLoading,
            pokemon = pokemon,
            isNetworkErrorDialogVisible = isNetworkErrorDialogVisible,
            isServerErrorDialogVisible = isServerErrorDialogVisible,
        ) { event ->
            when (event) {
                is ListDetailUiEvent.OnBackClick -> navigator.pop()
                is ListDetailUiEvent.OnFavoritesButtonClick -> addFavoritePokemon(pokemon)
                is ListDetailUiEvent.OnRetryButtonClick -> refresh(error = event.errorType)
            }
        }
    }

    override fun setServerErrorDialogVisible(flag: Boolean) {
        setServerErrorDialogVisibleCallback?.invoke(flag)
    }

    override fun setNetworkErrorDialogVisible(flag: Boolean) {
        setNetworkErrorDialogVisibleCallback?.invoke(flag)
    }

    @CircuitInject(ListDetailScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(
            screen: ListDetailScreen,
            navigator: Navigator,
        ): ListDetailPresenter
    }
}
