package com.easyhooon.pokedex

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.util.DebugLogger
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.logger.Level
import com.easyhooon.pokedex.di.module.AppModule
import org.koin.dsl.KoinConfiguration
import org.koin.ksp.generated.module

@OptIn(KoinExperimentalAPI::class)
class PokedexApplication : Application(), ImageLoaderFactory, KoinStartup {
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .diskCache {
                DiskCache.Builder()
                    .directory(cacheDir.resolve("image_cache"))
                    .maxSizeBytes(10 * 1024 * 1024)
                    .build()
            }
            .logger(DebugLogger())
            .respectCacheHeaders(false)
            .build()
    }

    override fun onKoinStartup() =
        KoinConfiguration {
            androidLogger(level = Level.DEBUG)
            androidContext(applicationContext)
            modules(AppModule().module)
        }
}
