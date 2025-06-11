@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.pokedex.android.feature)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.easyhooon.pokedex.feature.main"
}

dependencies {
    implementations(
        projects.feature.favorites,
        projects.feature.favoritesDetail,
        projects.feature.list,
        projects.feature.listDetail,

        libs.kotlinx.collections.immutable,

        libs.androidx.activity.compose,
        libs.androidx.splash,

        libs.timber,
        libs.compose.system.ui.controller,
    )
}
