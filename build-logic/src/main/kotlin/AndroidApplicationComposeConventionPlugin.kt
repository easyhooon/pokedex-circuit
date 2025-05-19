import com.android.build.api.dsl.ApplicationExtension
import com.easyhooon.pokedex.convention.Plugins
import com.easyhooon.pokedex.convention.applyPlugins
import com.easyhooon.pokedex.convention.configureCompose
import org.gradle.kotlin.dsl.configure

internal class AndroidApplicationComposeConventionPlugin : BuildLogicConventionPlugin(
    {
        applyPlugins(Plugins.ANDROID_APPLICATION, Plugins.KOTLIN_COMPOSE)

        extensions.configure<ApplicationExtension> {
            configureCompose(this)
        }
    },
)
