@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.pokedex.android.library)
}

android {
    namespace = "com.easyhooon.pokedex.core.data.api"
}

dependencies {
    implementations(
        projects.core.common,
        projects.core.model,

        libs.kotlinx.coroutines.core,

        libs.androidx.paging.runtime,

        libs.timber,
    )
}
