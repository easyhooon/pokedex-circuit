package com.easyhooon.pokedex.core.network.di

import com.easyhooon.pokedex.core.network.service.PokemonService
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class ApiModule {
    @Single
    fun providePokemonService(retrofit: Retrofit): PokemonService =
        retrofit.create(PokemonService::class.java)
}
