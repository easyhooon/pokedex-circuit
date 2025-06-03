@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.pokedex.android.retrofit)
    alias(libs.plugins.pokedex.android.koin)
    alias(libs.plugins.kotlin.serialization)
}

val localPropertiesFile = rootProject.file("local.properties")
val localProperties = Properties()
localProperties.load(FileInputStream(localPropertiesFile))

android {
    namespace = "com.easyhooon.pokedex.core.network"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementations(
        libs.timber,
    )
}
