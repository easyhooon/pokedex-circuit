package com.easyhooon.pokedex.core.database.di

import android.content.Context
import androidx.room.Room
import com.easyhooon.pokedex.core.database.FavoritesPokemonDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get<Context>().applicationContext,
            FavoritesPokemonDatabase::class.java,
            "favorites_pokemon_database",
        ).build()
    }
}