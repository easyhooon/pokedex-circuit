package com.easyhooon.pokedex.core.data.impl.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.easyhooon.pokedex.core.common.InsertFavoriteResult
import com.easyhooon.pokedex.core.data.api.repository.PokemonRepository
import com.easyhooon.pokedex.core.data.impl.paging.PokemonPagingSource
import com.easyhooon.pokedex.core.data.impl.mapper.toEntity
import com.easyhooon.pokedex.core.data.impl.mapper.toModel
import com.easyhooon.pokedex.core.data.impl.util.Constants
import com.easyhooon.pokedex.core.data.impl.util.runSuspendCatching
import com.easyhooon.pokedex.core.database.FavoritesPokemonDao
import com.easyhooon.pokedex.core.model.PokemonDetailModel
import com.easyhooon.pokedex.core.model.PokemonModel
import com.easyhooon.pokedex.core.network.service.PokemonService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DefaultPokemonRepository @Inject constructor(
    private val service: PokemonService,
    private val dao: FavoritesPokemonDao,
) : PokemonRepository {
    override fun getPokemonList(): Flow<PagingData<PokemonModel>> {
        val pagingSourceFactory = {
            PokemonPagingSource(service)
        }
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGING_SIZE,
                enablePlaceholders = true,
            ),
            pagingSourceFactory = pagingSourceFactory,
        ).flow.map { pagingData ->
            pagingData.map { pokemon ->
                pokemon.toModel()
            }
        }
    }

    override suspend fun getPokemonDetail(name: String) = runSuspendCatching {
        service.getPokemonDetail(name).toModel()
    }

    override fun getFavoritesPokemonList(): Flow<List<PokemonDetailModel>> {
        return dao.getFavoritesPokemonList().map { pokemonList ->
            pokemonList.map { pokemonEntity ->
                pokemonEntity.toModel()
            }
        }
    }

    override suspend fun insertFavoritePokemon(pokemon: PokemonDetailModel): InsertFavoriteResult {
        return dao.insertPokemonWithResult(pokemon.toEntity())
    }

    override suspend fun deleteFavoritePokemon(pokemon: PokemonDetailModel): Int {
        return dao.deletePokemon(pokemon.toEntity())
    }
}
