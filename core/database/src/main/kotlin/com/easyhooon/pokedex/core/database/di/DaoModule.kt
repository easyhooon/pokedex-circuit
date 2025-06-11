package com.easyhooon.pokedex.core.database.di

import com.easyhooon.pokedex.core.database.FavoritesPokemonDao
import com.easyhooon.pokedex.core.database.FavoritesPokemonDatabase
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module(includes = [DatabaseModule::class])
class DaoModule {

    @Single
    fun provideFavoritesDao(
        database: FavoritesPokemonDatabase,
    ): FavoritesPokemonDao = database.favoritesDao()
}
