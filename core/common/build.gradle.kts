@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.pokedex.android.library.compose)
    alias(libs.plugins.pokedex.android.hilt)
    alias(libs.plugins.pokedex.android.retrofit)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.easyhooon.pokedex.core.common"
}

dependencies {
    implementations(
        projects.core.model,

        libs.kotlinx.collections.immutable,

        libs.androidx.hilt.navigation.compose,

        libs.timber,
        libs.bundles.androidx.lifecycle,
    )
}
