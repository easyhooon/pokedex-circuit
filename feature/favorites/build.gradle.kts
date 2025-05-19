@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.pokedex.android.feature)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.easyhooon.pokedex.feature.favorites"
}

dependencies {
    implementations(
        libs.kotlinx.collections.immutable,

        libs.timber,
    )
}
