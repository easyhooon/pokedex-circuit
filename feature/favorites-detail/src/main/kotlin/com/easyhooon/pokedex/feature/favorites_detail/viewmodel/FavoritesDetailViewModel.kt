package com.easyhooon.pokedex.feature.favorites_detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.easyhooon.pokedex.core.common.UiText
import com.easyhooon.pokedex.core.data.api.repository.PokemonRepository
import com.easyhooon.pokedex.core.model.PokemonDetailModel
import com.easyhooon.pokedex.core.navigation.Route
import com.easyhooon.pokedex.core.navigation.Route.FavoritesDetail.Companion.typeMap
import com.easyhooon.pokedex.feature.favorites_detail.R
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import timber.log.Timber

@KoinViewModel
class FavoritesDetailViewModel(
    private val repository: PokemonRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val pokemon = savedStateHandle.toRoute<Route.FavoritesDetail>(typeMap).pokemon

    private val _uiState = MutableStateFlow(FavoritesDetailUiState())
    val uiState: StateFlow<FavoritesDetailUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<FavoritesDetailUiEvent>()
    val uiEvent: Flow<FavoritesDetailUiEvent> = _uiEvent.receiveAsFlow()

    init {
        _uiState.update {
            it.copy(pokemon = pokemon)
        }
    }

    fun onAction(action: FavoritesDetailUiAction) {
        when (action) {
            is FavoritesDetailUiAction.OnBackClick -> navigateBack()
            is FavoritesDetailUiAction.OnFavoritesButtonClick -> removeFavoritePokemon(action.pokemon)
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _uiEvent.send(FavoritesDetailUiEvent.NavigateBack)
        }
    }

    @Suppress("TooGenericExceptionCaught")
    private fun removeFavoritePokemon(pokemon: PokemonDetailModel) {
        viewModelScope.launch {
            try {
                val affectedRows = repository.deleteFavoritePokemon(pokemon)
                if (affectedRows > 0) {
                    _uiEvent.send(FavoritesDetailUiEvent.ShowToast(UiText.StringResource(R.string.remove_success)))
                } else {
                    _uiEvent.send(FavoritesDetailUiEvent.ShowToast(UiText.StringResource(R.string.remove_failed)))
                }
            } catch (e: Exception) {
                Timber.e(e)
                _uiEvent.send(FavoritesDetailUiEvent.ShowToast(UiText.StringResource(R.string.remove_failed)))
            }
        }
    }
}
