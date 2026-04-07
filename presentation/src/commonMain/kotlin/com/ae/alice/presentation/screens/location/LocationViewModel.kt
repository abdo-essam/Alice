package com.ae.alice.presentation.screens.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationViewModel : ViewModel() {

    private val _state = MutableStateFlow(LocationState())
    val state: StateFlow<LocationState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<LocationEffect>()
    val effect: SharedFlow<LocationEffect> = _effect.asSharedFlow()

    fun processIntent(intent: LocationIntent) {
        when (intent) {
            is LocationIntent.OnCameraMove -> updateLocation(intent.latitude, intent.longitude)
            is LocationIntent.ConfirmClick -> confirmLocation()
            is LocationIntent.MyLocationClick -> getMyLocation()
            is LocationIntent.BackClick -> navigateBack()
        }
    }

    private fun updateLocation(lat: Double, lng: Double) {
        _state.update { 
            it.copy(
                latitude = lat,
                longitude = lng,
                addressName = "Moc Location (${lat.toString().take(6)}, ${lng.toString().take(6)})"
            ) 
        }
    }

    private fun confirmLocation() {
        viewModelScope.launch {
            val s = _state.value
            _effect.emit(LocationEffect.SubmitLocation(s.latitude, s.longitude, s.addressName))
            _effect.emit(LocationEffect.NavigateBack)
        }
    }

    private fun getMyLocation() {
        // Mock get user location, for now moves to a specific point (e.g. Jeddah)
        val lat = 21.4858
        val lng = 39.1925
        updateLocation(lat, lng)
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _effect.emit(LocationEffect.NavigateBack)
        }
    }
}
