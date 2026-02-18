package com.ae.alice.domain.usecase

import com.ae.alice.domain.model.AppLanguage
import com.ae.alice.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class ManageLanguageUseCase(
    private val settingsRepository: SettingsRepository
) {
    fun getAppLanguage(): Flow<AppLanguage> = settingsRepository.getAppLanguage()

    suspend fun setAppLanguage(language: AppLanguage) = settingsRepository.setAppLanguage(language)
}
