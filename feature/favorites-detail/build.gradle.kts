@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.pokedex.android.feature)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.easyhooon.pokedex.feature.favorites_detail"

    buildFeatures {
        buildConfig = true
    }
}

ksp {
    arg("circuit.codegen.mode", "hilt")
}

dependencies {
    implementations(
        libs.kotlinx.collections.immutable,

        libs.timber,

        libs.bundles.androidx.paging,
    )
}
