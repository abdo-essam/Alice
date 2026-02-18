package com.ae.alice.domain.repository

import com.ae.alice.domain.model.AppLanguage
import com.ae.alice.domain.model.AppTheme
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getAppTheme(): Flow<AppTheme>
    suspend fun setAppTheme(theme: AppTheme)

    fun getAppLanguage(): Flow<AppLanguage>
    suspend fun setAppLanguage(language: AppLanguage)
}
