@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.pokedex.android.feature)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.easyhooon.pokedex.feature.favorites_detail"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementations(
        libs.kotlinx.collections.immutable,

        libs.timber,
    )
}
