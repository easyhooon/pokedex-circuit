@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.pokedex.android.feature)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.easyhooon.pokedex.feature.main"
}

ksp {
    arg("circuit.codegen.mode", "hilt")
}

dependencies {
    implementations(
        libs.kotlinx.collections.immutable,

        libs.androidx.activity.compose,
        libs.androidx.splash,

        libs.timber,
        libs.compose.system.ui.controller,
    )
}
