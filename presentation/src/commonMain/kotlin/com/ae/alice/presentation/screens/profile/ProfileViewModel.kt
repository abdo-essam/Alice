package com.ae.alice.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Profile screen ViewModel.
 */
class ProfileViewModel : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ProfileEffect>()
    val effect = _effect.asSharedFlow()

    init {
        _state.value = ProfileState(
            fullName = "Alice User",
            username = "alice_user",
            profileImageUrl = "",
            versionNumber = "1.0.0"
        )
    }

    fun processIntent(intent: ProfileIntent) {
        viewModelScope.launch {
            when (intent) {
                ProfileIntent.LoadProfile -> { /* reload */ }
                ProfileIntent.EditProfile -> _effect.emit(ProfileEffect.NavigateToEditProfile)
                ProfileIntent.ChangePassword -> _effect.emit(ProfileEffect.NavigateToChangePassword)
                ProfileIntent.ManageAddresses -> _effect.emit(ProfileEffect.NavigateToAddresses)
                ProfileIntent.ChangeLanguage -> { /* show language dialog */ }
                ProfileIntent.ChangeTheme -> { /* show theme dialog */ }
                ProfileIntent.PrivacyPolicy -> _effect.emit(ProfileEffect.NavigateToPrivacyPolicy)
                ProfileIntent.ContactUs -> _effect.emit(ProfileEffect.NavigateToContactUs)
                ProfileIntent.Logout -> _effect.emit(ProfileEffect.LoggedOut)
            }
        }
    }
}
