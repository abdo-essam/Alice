package com.ae.alice.presentation.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ae.alice.domain.model.AppLanguage
import com.ae.alice.domain.model.AppTheme
import com.ae.alice.domain.usecase.ManageLanguageUseCase
import com.ae.alice.domain.usecase.ManageThemeUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val manageThemeUseCase: ManageThemeUseCase,
    private val manageLanguageUseCase: ManageLanguageUseCase
) : ViewModel() {

    val currentTheme: StateFlow<AppTheme> = manageThemeUseCase.getAppTheme()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AppTheme.SYSTEM
        )

    val currentLanguage: StateFlow<AppLanguage> = manageLanguageUseCase.getAppLanguage()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AppLanguage.English
        )

    fun onThemeChanged(theme: AppTheme) {
        viewModelScope.launch {
            manageThemeUseCase.setAppTheme(theme)
        }
    }

    fun onLanguageChanged(language: AppLanguage) {
        viewModelScope.launch {
            manageLanguageUseCase.setAppLanguage(language)
        }
    }
}
