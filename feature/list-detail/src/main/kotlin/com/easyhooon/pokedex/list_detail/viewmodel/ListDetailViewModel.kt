package com.easyhooon.pokedex.list_detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.easyhooon.pokedex.core.common.ErrorHandlerActions
import com.easyhooon.pokedex.core.common.InsertFavoriteResult
import com.easyhooon.pokedex.core.common.UiText
import com.easyhooon.pokedex.core.common.handleException
import com.easyhooon.pokedex.core.data.api.repository.PokemonRepository
import com.easyhooon.pokedex.core.model.PokemonDetailModel
import com.easyhooon.pokedex.core.navigation.Route
import com.easyhooon.pokedex.feature.list_detail.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListDetailViewModel @Inject constructor(
    private val repository: PokemonRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), ErrorHandlerActions {
    private val name = savedStateHandle.toRoute<Route.ListDetail>().name

    private val _uiState = MutableStateFlow(ListDetailUiState())
    val uiState: StateFlow<ListDetailUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<ListDetailUiEvent>()
    val uiEvent: Flow<ListDetailUiEvent> = _uiEvent.receiveAsFlow()

    init {
        getPokemonDetail(name)
    }

    fun onAction(action: ListDetailUiAction) {
        when (action) {
            is ListDetailUiAction.OnBackClick -> navigateBack()
            is ListDetailUiAction.OnButtonClick -> addFavoritePokemon(action.pokemon)
            is ListDetailUiAction.OnRetryClick -> refresh(action.error)
        }
    }

    private fun getPokemonDetail(name: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.getPokemonDetail(name)
                .onSuccess { pokemon ->
                    _uiState.update {
                        it.copy(pokemon = pokemon)
                    }
                }
                .onFailure { throwable ->
                    handleException(throwable, this@ListDetailViewModel)
                }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _uiEvent.send(ListDetailUiEvent.NavigateBack)
        }
    }

    private fun addFavoritePokemon(pokemon: PokemonDetailModel) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (repository.insertFavoritePokemon(pokemon)) {
                is InsertFavoriteResult.Success -> {
                    _uiEvent.send(ListDetailUiEvent.ShowToast(UiText.StringResource(R.string.favorites_add_success)))
                }

                is InsertFavoriteResult.Duplicate -> {
                    _uiEvent.send(ListDetailUiEvent.ShowToast(UiText.StringResource(R.string.favorites_duplicate)))
                }

                is InsertFavoriteResult.LimitExceeded -> {
                    _uiEvent.send(ListDetailUiEvent.ShowToast(UiText.StringResource(R.string.favorites_max_limit)))
                }
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private fun refresh(error: ErrorType) {
        getPokemonDetail(name)

        when (error) {
            ErrorType.NETWORK -> setNetworkErrorDialogVisible(false)
            ErrorType.SERVER -> setServerErrorDialogVisible(false)
        }
    }

    override fun setServerErrorDialogVisible(flag: Boolean) {
        _uiState.update {
            it.copy(isServerErrorDialogVisible = flag)
        }
    }

    override fun setNetworkErrorDialogVisible(flag: Boolean) {
        _uiState.update {
            it.copy(isNetworkErrorDialogVisible = flag)
        }
    }
}
