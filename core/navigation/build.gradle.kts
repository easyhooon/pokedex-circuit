@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.pokedex.android.library.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.easyhooon.pokedex.core.navigation"
}

dependencies {
    implementations(
        projects.core.model,

        libs.kotlinx.serialization.json,

        libs.androidx.navigation.compose,
    )
}
