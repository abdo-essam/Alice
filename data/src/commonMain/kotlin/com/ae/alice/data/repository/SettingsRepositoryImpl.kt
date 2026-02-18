package com.ae.alice.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ae.alice.domain.model.AppLanguage
import com.ae.alice.domain.model.AppTheme
import com.ae.alice.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {

    private val KEY_THEME = stringPreferencesKey("app_theme")
    private val KEY_LANGUAGE = stringPreferencesKey("app_language")

    override fun getAppTheme(): Flow<AppTheme> {
        return dataStore.data.map { preferences ->
            val themeName = preferences[KEY_THEME] ?: AppTheme.SYSTEM.name
            try {
                AppTheme.valueOf(themeName)
            } catch (e: Exception) {
                AppTheme.SYSTEM
            }
        }
    }

    override suspend fun setAppTheme(theme: AppTheme) {
        dataStore.edit { preferences ->
            preferences[KEY_THEME] = theme.name
        }
    }

    override fun getAppLanguage(): Flow<AppLanguage> {
        return dataStore.data.map { preferences ->
            val langName = preferences[KEY_LANGUAGE] ?: AppLanguage.English.name
            try {
                AppLanguage.valueOf(langName)
            } catch (e: Exception) {
                AppLanguage.English
            }
        }
    }

    override suspend fun setAppLanguage(language: AppLanguage) {
        dataStore.edit { preferences ->
            preferences[KEY_LANGUAGE] = language.name
        }
    }
}
