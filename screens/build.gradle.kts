@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.easyhooon.pokedex.screens"
}

dependencies {
    implementation(projects.core.model)
    api(libs.circuit.runtime)
}
