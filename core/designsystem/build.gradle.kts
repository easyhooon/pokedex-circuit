@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.pokedex.android.library.compose)
}

android {
    namespace = "com.easyhooon.pokedex.core.designsystem"
}

dependencies {
    implementations(
        projects.core.common,

        libs.androidx.splash,
        libs.coil.compose,
        libs.timber,
        libs.compose.keyboard.state,
        libs.compose.effects,

        libs.bundles.landscapist,
    )
}
