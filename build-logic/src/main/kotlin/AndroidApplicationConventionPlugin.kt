import com.android.build.api.dsl.ApplicationExtension
import com.easyhooon.pokedex.convention.Plugins
import com.easyhooon.pokedex.convention.applyPlugins
import com.easyhooon.pokedex.convention.configureAndroid
import com.easyhooon.pokedex.convention.libs
import org.gradle.kotlin.dsl.configure

internal class AndroidApplicationConventionPlugin : BuildLogicConventionPlugin(
    {
        applyPlugins(Plugins.ANDROID_APPLICATION, Plugins.KOTLIN_ANDROID)

        extensions.configure<ApplicationExtension> {
            configureAndroid(this)

            defaultConfig {
                targetSdk = libs.versions.targetSdk.get().toInt()
                versionCode = libs.versions.versionCode.get().toInt()
                versionName = libs.versions.versionName.get()
            }
        }
    },
)
