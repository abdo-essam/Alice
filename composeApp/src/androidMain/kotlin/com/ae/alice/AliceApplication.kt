package com.ae.alice

import android.app.Application
import android.content.Context
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
        com.ae.alice.data.local.applicationContext = this
        initKoin(
            platformModules = listOf(androidNetworkModule)
        ) {
            androidLogger()
            androidContext(this@AliceApplication)
        }
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}
