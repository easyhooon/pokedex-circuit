@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.pokedex.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.easyhooon.pokedex.core.data.impl"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementations(
        projects.core.common,
        projects.core.data.api,
        projects.core.database,
        projects.core.model,
        projects.core.network,

        libs.androidx.paging.runtime,

        libs.timber,
    )
}
