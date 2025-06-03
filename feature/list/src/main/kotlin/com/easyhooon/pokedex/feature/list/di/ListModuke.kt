package com.easyhooon.pokedex.feature.list.di

import com.easyhooon.pokedex.feature.list.viewmodel.ListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val listModule =
    module {
        viewModelOf(::ListViewModel)
    }
