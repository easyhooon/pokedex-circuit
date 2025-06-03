package com.easyhooon.pokedex.core.data.impl.di

import org.koin.dsl.module
import com.easyhooon.pokedex.core.data.api.repository.PokemonRepository
import com.easyhooon.pokedex.core.data.impl.repository.DefaultPokemonRepository
import com.easyhooon.pokedex.core.database.di.daoModule
import com.easyhooon.pokedex.core.network.di.apiModule
import com.easyhooon.pokedex.core.network.di.networkModule

val dataModule =
    module {
        includes(
            daoModule,
            apiModule,
            networkModule,
        )
        single<PokemonRepository> {
            DefaultPokemonRepository(
                service = get(),
                dao = get(),
            )
        }
    }
