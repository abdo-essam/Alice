package com.ae.alice.presentation.screens.location

data class LocationState(
    val latitude: Double = 24.7136, // Riyadh default
    val longitude: Double = 46.6753,
    val isLoading: Boolean = false,
    val isConfirmEnabled: Boolean = true,
    val addressName: String = ""
)

sealed interface LocationIntent {
    data class OnCameraMove(val latitude: Double, val longitude: Double) : LocationIntent
    data object ConfirmClick : LocationIntent
    data object MyLocationClick : LocationIntent
    data object BackClick : LocationIntent
}

sealed interface LocationEffect {
    data object NavigateBack : LocationEffect
    data class SubmitLocation(val latitude: Double, val longitude: Double, val address: String) : LocationEffect
}
