import com.easyhooon.pokedex.convention.Plugins
import com.easyhooon.pokedex.convention.applyPlugins
import com.easyhooon.pokedex.convention.implementation
import com.easyhooon.pokedex.convention.ksp
import com.easyhooon.pokedex.convention.libs
import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.configure

internal class AndroidKoinConventionPlugin : BuildLogicConventionPlugin(
    {
        applyPlugins(Plugins.KSP)

        extensions.configure<KspExtension> {
            arg("KOIN_CONFIG_CHECK", "true")
            arg("KOIN_DEFAULT_MODULE", "false")
        }

        dependencies {
            implementation(libs.koin.android)
            implementation(libs.koin.annotations)
            ksp(libs.koin.ksp.compiler)
        }
    },
)
