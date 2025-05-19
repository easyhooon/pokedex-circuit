rootProject.name = "pokedex-circuit"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

include(
    ":app",

    ":core:common",
    ":core:data:api",
    ":core:data:impl",
    ":core:database",
    ":core:designsystem",
    ":core:model",
    ":core:navigation",
    ":core:network",

    ":feature:favorites",
    ":feature:list-detail",
    ":feature:list",
    ":feature:favorites-detail",
    ":feature:main",
)
