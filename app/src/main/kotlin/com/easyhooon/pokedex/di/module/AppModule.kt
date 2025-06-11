package com.easyhooon.pokedex.di.module

import com.easyhooon.pokedex.core.data.impl.di.DataModule
import com.easyhooon.pokedex.feature.favorites.di.FavoritesModule
import com.easyhooon.pokedex.feature.favorites_detail.di.FavoritesDetailModule
import com.easyhooon.pokedex.feature.list.di.ListModule
import com.easyhooon.pokedex.feature.list_detail.di.ListDetailModule
import org.koin.core.annotation.Module

@Module(
    includes = [
        DataModule::class,
        FavoritesModule::class,
        FavoritesDetailModule::class,
        ListModule::class,
        ListDetailModule::class
    ]
)
class AppModule
