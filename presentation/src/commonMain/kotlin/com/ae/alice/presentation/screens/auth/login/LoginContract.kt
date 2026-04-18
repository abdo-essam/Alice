package com.ae.alice.presentation.screens.auth.login

import com.ae.alice.presentation.base.UiEffect
import com.ae.alice.presentation.base.UiIntent
import com.ae.alice.presentation.base.UiState

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val isLoginEnabled: Boolean = false,
) : UiState

sealed interface LoginIntent : UiIntent {
    data class EmailChanged(val email: String) : LoginIntent
    data class PasswordChanged(val password: String) : LoginIntent
    data object TogglePasswordVisibility : LoginIntent
    data object LoginClicked : LoginIntent
    data object RegisterClicked : LoginIntent
    data object ForgotPasswordClicked : LoginIntent
    data object GoogleSignInClicked : LoginIntent
    data object SkipClicked : LoginIntent
}

sealed interface LoginEffect : UiEffect {
    data object NavigateToMain : LoginEffect
    data object NavigateToRegister : LoginEffect
    data object NavigateToForgotPassword : LoginEffect
    data class ShowError(val message: String) : LoginEffect
}
