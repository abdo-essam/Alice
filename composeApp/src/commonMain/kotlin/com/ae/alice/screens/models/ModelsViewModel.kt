package com.ae.alice.screens.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.ae.alice.core.domain.entity.CarModel
import com.ae.alice.core.domain.repository.CarModelRepository

/**
 * ViewModel for ModelsScreen.
 */
class ModelsViewModel(
    private val carModelRepository: CarModelRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ModelsUiState())
    val uiState: StateFlow<ModelsUiState> = _uiState.asStateFlow()
    
    fun loadModels(brandId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                val models = carModelRepository.getModelsByBrand(brandId)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    models = models
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load models"
                )
            }
        }
    }
}

/**
 * UI State for ModelsScreen.
 */
data class ModelsUiState(
    val isLoading: Boolean = false,
    val models: List<CarModel> = emptyList(),
    val error: String? = null
)
