package com.easyhooon.pokedex.initializer

import android.content.Context
import androidx.startup.Initializer
import com.easyhooon.pokedex.BuildConfig
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        if (com.easyhooon.pokedex.BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement) =
                    "${com.easyhooon.pokedex.BuildConfig.APPLICATION_ID}://${element.fileName}:${element.lineNumber}#${element.methodName}"
            })
        }
    }
    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
