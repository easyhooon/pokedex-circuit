package com.easyhooon.pokedex.feature.list_detail.di

import com.easyhooon.pokedex.feature.list_detail.viewmodel.ListDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val listDetailModule =
    module {
        viewModelOf(::ListDetailViewModel)
    }