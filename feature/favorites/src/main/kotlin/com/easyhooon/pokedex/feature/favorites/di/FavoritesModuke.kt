package com.easyhooon.pokedex.feature.favorites.di

import com.easyhooon.pokedex.feature.favorites.viewmodel.FavoritesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val favoritesModule =
    module {
        viewModelOf(::FavoritesViewModel)
    }