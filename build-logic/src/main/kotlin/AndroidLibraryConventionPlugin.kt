import com.android.build.gradle.LibraryExtension
import com.easyhooon.pokedex.convention.Plugins
import com.easyhooon.pokedex.convention.applyPlugins
import com.easyhooon.pokedex.convention.configureAndroid
import com.easyhooon.pokedex.convention.libs
import org.gradle.kotlin.dsl.configure

internal class AndroidLibraryConventionPlugin : BuildLogicConventionPlugin({
    applyPlugins(Plugins.ANDROID_LIBRARY, Plugins.KOTLIN_ANDROID)

    extensions.configure<LibraryExtension> {
        configureAndroid(this)

        defaultConfig.apply {
            targetSdk = libs.versions.targetSdk.get().toInt()
        }
    }
})
