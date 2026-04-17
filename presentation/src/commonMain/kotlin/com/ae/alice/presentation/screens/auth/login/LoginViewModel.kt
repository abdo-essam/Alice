package com.ae.alice.presentation.screens.auth.login

import androidx.lifecycle.viewModelScope
import com.ae.alice.domain.repository.AuthRepository
import com.ae.alice.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : BaseViewModel<LoginState, LoginIntent, LoginEffect>(LoginState()) {

    override fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.EmailChanged -> updateState {
                copy(email = intent.email, isLoginEnabled = isFormValid(intent.email, password))
            }
            is LoginIntent.PasswordChanged -> updateState {
                copy(password = intent.password, isLoginEnabled = isFormValid(email, intent.password))
            }
            LoginIntent.TogglePasswordVisibility -> updateState {
                copy(isPasswordVisible = !isPasswordVisible)
            }
            LoginIntent.LoginClicked -> login()
            LoginIntent.RegisterClicked -> emitEffect(LoginEffect.NavigateToRegister)
            LoginIntent.ForgotPasswordClicked -> emitEffect(LoginEffect.NavigateToForgotPassword)
            LoginIntent.GoogleSignInClicked -> {
                // UI handles Google Sign in with GoogleButtonUiContainer. 
                // Once successful, it will call loginWithGoogle() directly on ViewModel.
            }
            LoginIntent.SkipClicked -> emitEffect(LoginEffect.NavigateToMain)
        }
    }

    private fun login() {
        viewModelScope.launch(Dispatchers.Default) {
            updateState { copy(isLoading = true) }
            try {
                authRepository.login(currentState.email.trim(), currentState.password)
                updateState { copy(isLoading = false) }
                emitEffect(LoginEffect.NavigateToMain)
            } catch (e: Exception) {
                updateState { copy(isLoading = false) }
                emitEffect(LoginEffect.ShowError(e.message ?: "Login failed"))
            }
        }
    }

    fun loginWithGoogle(idToken: String, accessToken: String?) {
        viewModelScope.launch(Dispatchers.Default) {
            updateState { copy(isLoading = true) }
            try {
                authRepository.loginWithGoogle(idToken, accessToken)
                updateState { copy(isLoading = false) }
                emitEffect(LoginEffect.NavigateToMain)
            } catch (e: Exception) {
                updateState { copy(isLoading = false) }
                emitEffect(LoginEffect.ShowError(e.message ?: "Google Sign-In failed"))
            }
        }
    }

    private fun isFormValid(email: String, password: String): Boolean {
        return email.contains("@") && email.contains(".") && password.length >= 6
    }
}
