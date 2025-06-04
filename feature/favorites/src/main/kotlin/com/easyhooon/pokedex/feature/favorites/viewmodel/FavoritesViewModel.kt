package com.easyhooon.pokedex.feature.favorites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyhooon.pokedex.core.data.api.repository.PokemonRepository
import com.easyhooon.pokedex.core.model.PokemonDetailModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Provided

@KoinViewModel
class FavoritesViewModel(
    @Provided private val repository: PokemonRepository,
) : ViewModel() {
    val favoritesPokemonList = repository.getFavoritesPokemonList()

    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<FavoritesUiEvent>()
    val uiEvent: Flow<FavoritesUiEvent> = _uiEvent.receiveAsFlow()

    fun onHomeUiAction(action: FavoritesUiAction) {
        when (action) {
            is FavoritesUiAction.OnItemClick -> navigateToFavoritesDetail(action.pokemon)
        }
    }

    private fun navigateToFavoritesDetail(pokemon: PokemonDetailModel) {
        viewModelScope.launch {
            _uiEvent.send(FavoritesUiEvent.NavigateToFavoritesDetail(pokemon))
        }
    }
}
