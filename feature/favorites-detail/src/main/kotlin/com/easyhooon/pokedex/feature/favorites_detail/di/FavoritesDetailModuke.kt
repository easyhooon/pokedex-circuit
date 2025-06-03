package com.easyhooon.pokedex.feature.favorites_detail.di

import com.easyhooon.pokedex.feature.favorites_detail.viewmodel.FavoritesDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val favoritesDetailModule =
    module {
        viewModelOf(::FavoritesDetailViewModel)
    }