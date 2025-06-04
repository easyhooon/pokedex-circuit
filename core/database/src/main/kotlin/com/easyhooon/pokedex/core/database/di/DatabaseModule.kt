package com.easyhooon.pokedex.core.database.di

import android.content.Context
import androidx.room.Room
import com.easyhooon.pokedex.core.database.FavoritesPokemonDatabase
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class DatabaseModule {
    @Single
    fun provideDatabase(context: Context): FavoritesPokemonDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            FavoritesPokemonDatabase::class.java,
            "favorites_pokemon_database"
        ).build()
}
