import com.easyhooon.pokedex.convention.Plugins
import com.easyhooon.pokedex.convention.applyPlugins
import com.easyhooon.pokedex.convention.detektPlugins
import com.easyhooon.pokedex.convention.libs
import org.gradle.api.JavaVersion
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension

internal class JvmKotlinConventionPlugin : BuildLogicConventionPlugin({
    applyPlugins(Plugins.JAVA_LIBRARY, Plugins.KOTLIN_JVM)

    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    extensions.configure<KotlinProjectExtension> {
        jvmToolchain(17)
    }

    dependencies {
        detektPlugins(libs.detekt.formatting)
    }
})
