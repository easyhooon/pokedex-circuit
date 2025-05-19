package com.easyhooon.pokedex.core.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.easyhooon.pokedex.core.common.InsertFavoriteResult
import com.easyhooon.pokedex.core.database.entity.PokemonDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesPokemonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPokemonDirect(pokemon: PokemonDetailEntity): Long

    @Delete
    suspend fun deletePokemon(pokemon: PokemonDetailEntity): Int

    @Query("SELECT * FROM favorites")
    fun getFavoritesPokemonList(): Flow<List<PokemonDetailEntity>>

    @Query("SELECT COUNT(*) FROM favorites")
    suspend fun getFavoritesCount(): Int

    @Transaction
    suspend fun insertPokemonWithResult(pokemon: PokemonDetailEntity): InsertFavoriteResult {
        if (getFavoritesCount() >= 10) return InsertFavoriteResult.LimitExceeded
        val result = insertPokemonDirect(pokemon)
        return if (result == -1L) InsertFavoriteResult.Duplicate else InsertFavoriteResult.Success
    }
}
