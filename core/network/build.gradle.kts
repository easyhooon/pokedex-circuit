@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.pokedex.android.retrofit)
    alias(libs.plugins.pokedex.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.easyhooon.pokedex.core.network"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementations(
        libs.timber,
    )
}
