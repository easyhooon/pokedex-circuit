package com.easyhooon.pokedex.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.easyhooon.pokedex.core.database.entity.PokemonDetailEntity

@Database(
    entities = [PokemonDetailEntity::class],
    version = 1,
    exportSchema = true,
)
@TypeConverters(PokemonTypeSlotListConverter::class)
abstract class FavoritesPokemonDatabase : RoomDatabase() {
    abstract fun favoritesPokemonDao(): FavoritesPokemonDao
}
