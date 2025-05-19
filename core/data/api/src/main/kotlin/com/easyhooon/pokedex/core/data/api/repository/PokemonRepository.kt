package com.easyhooon.pokedex.core.data.api.repository

import androidx.paging.PagingData
import com.easyhooon.pokedex.core.common.InsertFavoriteResult
import com.easyhooon.pokedex.core.model.PokemonDetailModel
import com.easyhooon.pokedex.core.model.PokemonModel
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<PagingData<PokemonModel>>
    suspend fun getPokemonDetail(name: String): Result<PokemonDetailModel>
    fun getFavoritesPokemonList(): Flow<List<PokemonDetailModel>>
    suspend fun insertFavoritePokemon(pokemon: PokemonDetailModel): InsertFavoriteResult
    suspend fun deleteFavoritePokemon(pokemon: PokemonDetailModel): Int
}
