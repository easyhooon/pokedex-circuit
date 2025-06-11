package com.easyhooon.pokedex.core.data.impl.di

import com.easyhooon.pokedex.core.data.api.repository.PokemonRepository
import com.easyhooon.pokedex.core.data.impl.repository.DefaultPokemonRepository
import com.easyhooon.pokedex.core.database.FavoritesPokemonDao
import com.easyhooon.pokedex.core.database.di.DaoModule
import com.easyhooon.pokedex.core.network.di.ApiModule
import com.easyhooon.pokedex.core.network.service.PokemonService
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module(includes = [DaoModule::class, ApiModule::class])
class DataModule {
    @Single
    fun providePokemonRepository(
        service: PokemonService,
        dao: FavoritesPokemonDao,
    ): PokemonRepository = DefaultPokemonRepository(service, dao)
}
