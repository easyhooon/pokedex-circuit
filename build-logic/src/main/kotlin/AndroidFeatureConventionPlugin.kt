import com.easyhooon.pokedex.convention.applyPlugins
import com.easyhooon.pokedex.convention.implementation
import com.easyhooon.pokedex.convention.libs
import com.easyhooon.pokedex.convention.project
import org.gradle.kotlin.dsl.dependencies

internal class AndroidFeatureConventionPlugin : BuildLogicConventionPlugin(
    {
        applyPlugins(
            "pokedex.android.library",
            "pokedex.android.library.compose",
            "pokedex.android.koin",
        )

        dependencies {
            implementation(project(path = ":core:data:api"))
            implementation(project(path = ":core:common"))
            implementation(project(path = ":core:designsystem"))
            implementation(project(path = ":core:model"))
            implementation(project(path = ":core:navigation"))

            implementation(libs.androidx.navigation.compose)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)
            implementation(libs.compose.effects)
            implementation(libs.bundles.androidx.lifecycle)
        }
    },
)
