package com.easyhooon.pokedex.core.data.impl.di

import com.easyhooon.pokedex.core.data.api.repository.PokemonRepository
import com.easyhooon.pokedex.core.data.impl.repository.DefaultPokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindPokemonRepository(defaultPokemonRepository: DefaultPokemonRepository): PokemonRepository
}
