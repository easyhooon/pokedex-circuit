@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.pokedex.android.feature)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.easyhooon.pokedex.feature.list"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementations(
        projects.feature.listDetail,

        libs.kotlinx.collections.immutable,

        libs.timber,

        libs.bundles.androidx.paging,
    )
}
