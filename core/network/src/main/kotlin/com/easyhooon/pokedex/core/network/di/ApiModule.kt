package com.easyhooon.pokedex.core.network.di

import com.easyhooon.pokedex.core.network.service.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    internal fun providePokemonService(
        retrofit: Retrofit,
    ): PokemonService {
        return retrofit.create()
    }
}
