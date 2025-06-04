package com.easyhooon.pokedex.feature.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.easyhooon.pokedex.core.data.api.repository.PokemonRepository
import com.easyhooon.pokedex.core.model.PokemonModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Provided

@KoinViewModel
class ListViewModel(
    @Provided private val repository: PokemonRepository,
) : ViewModel() {
    private val _uiEvent = Channel<ListUiEvent>()
    val uiEvent: Flow<ListUiEvent> = _uiEvent.receiveAsFlow()

    private val basePokemonList = repository.getPokemonList().cachedIn(viewModelScope)
    private val favoriteIds = repository.getFavoritesPokemonList()
        .map { favorites -> favorites.map { it.id } }

    val pokemonList: Flow<PagingData<PokemonModel>> = combine(
        basePokemonList,
        favoriteIds
    ) { pagingData, favoriteIdsList ->
        pagingData.map { pokemon ->
            pokemon.copy(isFavorite = favoriteIdsList.contains(pokemon.getId()))
        }
    }

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
