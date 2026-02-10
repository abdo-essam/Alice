package com.ae.alice

import android.app.Application
import com.ae.alice.di.initKoin
import com.ae.alice.network.di.androidNetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

/**
 * Android Application class for Koin initialization.
 */
class AliceApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            platformModules = listOf(androidNetworkModule)
        ) {
            androidLogger()
            androidContext(this@AliceApplication)
        }
    }
}
