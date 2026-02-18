package com.ae.alice.domain.usecase

import com.ae.alice.domain.model.AppTheme
import com.ae.alice.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class ManageThemeUseCase(
    private val settingsRepository: SettingsRepository
) {
    fun getAppTheme(): Flow<AppTheme> = settingsRepository.getAppTheme()

    suspend fun setAppTheme(theme: AppTheme) = settingsRepository.setAppTheme(theme)
}
