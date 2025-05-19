package com.easyhooon.pokedex.feature.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.easyhooon.pokedex.core.data.api.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    repository: PokemonRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ListUiState())
    val uiState: StateFlow<ListUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<ListUiEvent>()
    val uiEvent: Flow<ListUiEvent> = _uiEvent.receiveAsFlow()

    val pokemonList = repository.getPokemonList()
        .cachedIn(viewModelScope)

    fun onAction(action: ListUiAction) {
        when (action) {
            is ListUiAction.OnItemClick -> navigateToListDetail(action.name)
            is ListUiAction.OnRetryButtonClick -> refetchPokemonList()
        }
    }

    private fun navigateToListDetail(name: String) {
        viewModelScope.launch {
            _uiEvent.send(ListUiEvent.NavigateToListDetail(name))
        }
    }

    private fun refetchPokemonList() {
        viewModelScope.launch {
            _uiEvent.send(ListUiEvent.RefetchPokemonList)
        }
    }
}
