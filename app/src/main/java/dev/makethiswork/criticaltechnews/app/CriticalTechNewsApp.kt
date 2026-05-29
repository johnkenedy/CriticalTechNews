package dev.makethiswork.criticaltechnews.app

import android.app.Application
import dev.makethiswork.criticaltechnews.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CriticalTechNewsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@CriticalTechNewsApp)
            modules(appModule)
        }
    }
}
