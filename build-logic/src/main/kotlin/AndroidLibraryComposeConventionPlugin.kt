import com.android.build.gradle.LibraryExtension
import com.easyhooon.pokedex.convention.Plugins
import com.easyhooon.pokedex.convention.applyPlugins
import com.easyhooon.pokedex.convention.configureCompose
import org.gradle.kotlin.dsl.configure

internal class AndroidLibraryComposeConventionPlugin : BuildLogicConventionPlugin(
    {
        applyPlugins(Plugins.ANDROID_LIBRARY, Plugins.KOTLIN_COMPOSE)

        extensions.configure<LibraryExtension> {
            configureCompose(this)
        }
    },
)


