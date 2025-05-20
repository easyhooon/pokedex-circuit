@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.pokedex.android.application)
    alias(libs.plugins.pokedex.android.application.compose)
    alias(libs.plugins.pokedex.android.hilt)
}

android {
    namespace = "com.easyhooon.pokedex"

    buildFeatures {
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

ksp {
    arg("circuit.codegen.mode", "hilt")
}

dependencies {
    implementations(
        projects.core.common,
        projects.core.data.api,
        projects.core.data.impl,
        projects.core.database,
        projects.core.designsystem,
        projects.core.model,
        projects.core.network,

        projects.feature.favorites,
        projects.feature.list,
        projects.feature.main,

        libs.androidx.activity.compose,
        libs.androidx.startup,
        libs.timber,
        libs.coil.compose,

        libs.bundles.circuit,
    )
    api(libs.circuit.codegen.annotation)
    ksp(libs.circuit.codegen.ksp)
}
