package com.easyhooon.pokedex.core.database.di

import com.easyhooon.pokedex.core.database.FavoritesPokemonDatabase
import com.easyhooon.pokedex.core.database.FavoritesPokemonDao
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module(includes = [DatabaseModule::class])
class DaoModule {
    @Single
    fun provideFavoritesPokemonDao(db: FavoritesPokemonDatabase): FavoritesPokemonDao =
        db.favoritesPokemonDao()
}
