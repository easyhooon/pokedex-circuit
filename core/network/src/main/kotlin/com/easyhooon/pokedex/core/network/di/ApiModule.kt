package com.easyhooon.pokedex.core.network.di

import com.easyhooon.pokedex.core.network.service.PokemonService
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.create

@Module(includes = [NetworkModule::class])
class ApiModule {

    @Single
    internal fun providePokemonService(
        retrofit: Retrofit,
    ): PokemonService {
        return retrofit.create()
    }
}
