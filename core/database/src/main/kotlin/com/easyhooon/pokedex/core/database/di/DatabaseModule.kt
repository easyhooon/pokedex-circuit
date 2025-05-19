package com.easyhooon.pokedex.core.database.di

import android.content.Context
import androidx.room.Room
import com.easyhooon.pokedex.core.database.FavoritesPokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideFavoritesDatabase(@ApplicationContext context: Context): FavoritesPokemonDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            FavoritesPokemonDatabase::class.java,
            "favorites_pokemon_database",
        ).build()
}
