import com.easyhooon.pokedex.convention.Plugins
import com.easyhooon.pokedex.convention.applyPlugins
import com.easyhooon.pokedex.convention.implementation
import com.easyhooon.pokedex.convention.ksp
import com.easyhooon.pokedex.convention.libs
import org.gradle.kotlin.dsl.dependencies

internal class AndroidHiltConventionPlugin : BuildLogicConventionPlugin(
    {
        applyPlugins(Plugins.HILT, Plugins.KSP)

        dependencies {
            implementation(libs.hilt.android)
            ksp(libs.hilt.android.compiler)
        }
    },
)
