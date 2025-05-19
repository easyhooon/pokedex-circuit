import com.easyhooon.pokedex.convention.Plugins
import com.easyhooon.pokedex.convention.applyPlugins
import com.easyhooon.pokedex.convention.implementation
import com.easyhooon.pokedex.convention.libs
import org.gradle.kotlin.dsl.dependencies

internal class AndroidRetrofitConventionPlugin : BuildLogicConventionPlugin(
    {
        applyPlugins(Plugins.KOTLINX_SERIALIZATION)

        dependencies {
            implementation(libs.retrofit)
            implementation(libs.retrofit.kotlinx.serialization.converter)
            implementation(libs.okhttp.logging.interceptor)
            implementation(libs.kotlinx.serialization.json)
        }
    },
)
