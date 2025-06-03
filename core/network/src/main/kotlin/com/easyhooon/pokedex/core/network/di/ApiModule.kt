package com.easyhooon.pokedex.core.network.di

import com.easyhooon.pokedex.core.network.service.PokemonService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single<PokemonService> {
        get<Retrofit>().create(PokemonService::class.java)
    }
}
