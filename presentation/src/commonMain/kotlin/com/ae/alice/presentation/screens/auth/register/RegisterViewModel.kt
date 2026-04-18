package com.ae.alice.presentation.screens.auth.register

import androidx.lifecycle.viewModelScope
import com.ae.alice.domain.repository.AuthRepository
import com.ae.alice.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authRepository: AuthRepository
) : BaseViewModel<RegisterState, RegisterIntent, RegisterEffect>(RegisterState()) {

    override fun handleIntent(intent: RegisterIntent) {
        when (intent) {
            is RegisterIntent.DisplayNameChanged -> updateState {
                copy(displayName = intent.name, isRegisterEnabled = isFormValid(intent.name, email, password, confirmPassword))
            }
            is RegisterIntent.EmailChanged -> updateState {
                copy(email = intent.email, isRegisterEnabled = isFormValid(displayName, intent.email, password, confirmPassword))
            }
            is RegisterIntent.PasswordChanged -> updateState {
                copy(password = intent.password, isRegisterEnabled = isFormValid(displayName, email, intent.password, confirmPassword))
            }
            is RegisterIntent.ConfirmPasswordChanged -> updateState {
                copy(confirmPassword = intent.confirmPassword, isRegisterEnabled = isFormValid(displayName, email, password, intent.confirmPassword))
            }
            RegisterIntent.TogglePasswordVisibility -> updateState {
                copy(isPasswordVisible = !isPasswordVisible)
            }
            RegisterIntent.ToggleConfirmPasswordVisibility -> updateState {
                copy(isConfirmPasswordVisible = !isConfirmPasswordVisible)
            }
            RegisterIntent.RegisterClicked -> register()
            RegisterIntent.LoginClicked -> emitEffect(RegisterEffect.NavigateToLogin)
            RegisterIntent.GoogleSignInClicked -> {
                // UI handles Google Sign in with GoogleButtonUiContainer. 
                // Once successful, it will call loginWithGoogle() directly on ViewModel.
            }
            RegisterIntent.SkipClicked -> emitEffect(RegisterEffect.NavigateToMain)
        }
    }

    private fun register() {
        viewModelScope.launch(Dispatchers.Default) {
            updateState { copy(isLoading = true) }
            try {
                authRepository.register(
                    email = currentState.email.trim(),
                    password = currentState.password,
                    displayName = currentState.displayName.trim()
                )
                updateState { copy(isLoading = false) }
                emitEffect(RegisterEffect.NavigateToMain)
            } catch (e: Exception) {
                updateState { copy(isLoading = false) }
                emitEffect(RegisterEffect.ShowError(e.message ?: "Registration failed"))
            }
        }
    }

    fun loginWithGoogle(idToken: String, accessToken: String?) {
        viewModelScope.launch(Dispatchers.Default) {
            updateState { copy(isLoading = true) }
            try {
                authRepository.loginWithGoogle(idToken, accessToken)
                updateState { copy(isLoading = false) }
                emitEffect(RegisterEffect.NavigateToMain)
            } catch (e: Exception) {
                updateState { copy(isLoading = false) }
                emitEffect(RegisterEffect.ShowError(e.message ?: "Google Sign-In failed"))
            }
        }
    }

    private fun isFormValid(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        return name.trim().length >= 2
            && email.contains("@") && email.contains(".")
            && password.length >= 6
            && password == confirmPassword
    }
}
