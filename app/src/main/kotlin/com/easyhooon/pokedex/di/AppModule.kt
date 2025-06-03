package com.easyhooon.pokedex.di

import org.koin.dsl.module
import com.easyhooon.pokedex.core.data.impl.di.dataModule
import com.easyhooon.pokedex.feature.favorites.di.favoritesModule
import com.easyhooon.pokedex.feature.favorites_detail.di.favoritesDetailModule
import com.easyhooon.pokedex.feature.list.di.listModule
import com.easyhooon.pokedex.feature.list_detail.di.listDetailModule

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
    }
