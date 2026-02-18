package com.ae.alice.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

lateinit var applicationContext: Context

actual fun createDataStore(context: Any?): DataStore<Preferences> {
    return createDataStore(
        producePath = {
            val ctx = context as? Context ?: applicationContext
            ctx.filesDir.resolve(dataStoreFileName).absolutePath
        }
    )
}
