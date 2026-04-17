package com.ae.alice.presentation.screens.auth.register

import com.ae.alice.presentation.base.UiEffect
import com.ae.alice.presentation.base.UiIntent
import com.ae.alice.presentation.base.UiState

data class RegisterState(
    val displayName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordVisible: Boolean = false,
    val isConfirmPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val isRegisterEnabled: Boolean = false,
) : UiState

sealed interface RegisterIntent : UiIntent {
    data class DisplayNameChanged(val name: String) : RegisterIntent
    data class EmailChanged(val email: String) : RegisterIntent
    data class PasswordChanged(val password: String) : RegisterIntent
    data class ConfirmPasswordChanged(val confirmPassword: String) : RegisterIntent
    data object TogglePasswordVisibility : RegisterIntent
    data object ToggleConfirmPasswordVisibility : RegisterIntent
    data object RegisterClicked : RegisterIntent
    data object LoginClicked : RegisterIntent
    data object GoogleSignInClicked : RegisterIntent
    data object SkipClicked : RegisterIntent
}

sealed interface RegisterEffect : UiEffect {
    data object NavigateToMain : RegisterEffect
    data object NavigateToLogin : RegisterEffect
    data class ShowError(val message: String) : RegisterEffect
}
