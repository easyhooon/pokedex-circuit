package com.easyhooon.pokedex.core.database.di

import com.easyhooon.pokedex.core.database.FavoritesPokemonDatabase
import org.koin.dsl.module

val daoModule = module {
    includes(databaseModule)

    single { get<FavoritesPokemonDatabase>().favoritesPokemonDao() }
}