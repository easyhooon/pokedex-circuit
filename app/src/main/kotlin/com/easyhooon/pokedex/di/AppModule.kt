package com.easyhooon.pokedex.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import com.easyhooon.pokedex.core.data.impl.di.dataModule
import com.easyhooon.pokedex.feature.favorites.di.favoritesModule
import com.easyhooon.pokedex.feature.favorites_detail.di.favoritesDetailModule
import com.easyhooon.pokedex.feature.list.di.listModule
import com.easyhooon.pokedex.feature.list_detail.di.listDetailModule
import com.easyhooon.pokedex.feature.favorites.viewmodel.FavoritesViewModel
import com.easyhooon.pokedex.feature.favorites_detail.viewmodel.FavoritesDetailViewModel
import com.easyhooon.pokedex.feature.list.viewmodel.ListViewModel
import com.easyhooon.pokedex.feature.list_detail.viewmodel.ListDetailViewModel

val featureModule =
    module {
        includes(
            favoritesModule,
            favoritesDetailModule,
            listModule,
            listDetailModule,
        )
    }

val appModule =
    module {
        includes(
            dataModule,
            featureModule,
        )
        viewModelOf(::FavoritesViewModel)
        viewModelOf(::FavoritesDetailViewModel)
        viewModelOf(::ListViewModel)
        viewModelOf(::ListDetailViewModel)
    }