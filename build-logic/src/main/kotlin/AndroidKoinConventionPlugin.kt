import com.easyhooon.pokedex.convention.Plugins
import com.easyhooon.pokedex.convention.applyPlugins
import com.easyhooon.pokedex.convention.implementation
import com.easyhooon.pokedex.convention.libs
import org.gradle.kotlin.dsl.dependencies

internal class AndroidKoinConventionPlugin : BuildLogicConventionPlugin(
    {
        applyPlugins(Plugins.KSP)

//        extensions.configure<KspExtension> {
//            arg("KOIN_CONFIG_CHECK", "true")
//            arg("KOIN_DEFAULT_MODULE", "false")
//        }

        dependencies {
            implementation(libs.koin.android)
//            implementation(libs.koin.annotations)
//            ksp(libs.koin.ksp.compiler)
        }
    },
) 