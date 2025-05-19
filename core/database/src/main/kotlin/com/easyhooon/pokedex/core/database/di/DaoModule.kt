package com.easyhooon.pokedex.core.database.di

import com.easyhooon.pokedex.core.database.FavoritesPokemonDao
import com.easyhooon.pokedex.core.database.FavoritesPokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideFavoritesDao(
        database: FavoritesPokemonDatabase,
    ): FavoritesPokemonDao = database.favoritesDao()
}
