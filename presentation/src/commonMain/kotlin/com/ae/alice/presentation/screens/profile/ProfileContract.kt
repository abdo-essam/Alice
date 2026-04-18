package com.ae.alice.presentation.screens.profile

/**
 * Profile screen UI state matching MENA's profile pattern.
 */
data class ProfileState(
    val fullName: String = "",
    val username: String = "",
    val profileImageUrl: String = "",
    val isLoading: Boolean = false,
    val versionNumber: String = "1.0.0",
)

/**
 * Profile intents.
 */
sealed interface ProfileIntent {
    data object LoadProfile : ProfileIntent
    data object EditProfile : ProfileIntent
    data object ChangePassword : ProfileIntent
    data object ManageAddresses : ProfileIntent
    data object ChangeLanguage : ProfileIntent
    data object ChangeTheme : ProfileIntent
    data object PrivacyPolicy : ProfileIntent
    data object ContactUs : ProfileIntent
    data object Logout : ProfileIntent
}

/**
 * Profile one-shot effects.
 */
sealed interface ProfileEffect {
    data object NavigateToEditProfile : ProfileEffect
    data object NavigateToChangePassword : ProfileEffect
    data object NavigateToAddresses : ProfileEffect
    data object NavigateToPrivacyPolicy : ProfileEffect
    data object NavigateToContactUs : ProfileEffect
    data object SwitchLanguage : ProfileEffect
    data object LoggedOut : ProfileEffect
}
