plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.easyhooon.pokedex.core.model"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    compileOnly(
        libs.compose.stable.marker,
    )
    implementation(libs.kotlinx.serialization.json)
}
