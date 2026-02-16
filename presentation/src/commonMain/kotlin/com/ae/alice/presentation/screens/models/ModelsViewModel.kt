package com.ae.alice.presentation.screens.models

import com.ae.alice.domain.entity.CarModel
import com.ae.alice.domain.repository.CarModelRepository
import com.ae.alice.presentation.base.BaseViewModel
import com.ae.alice.presentation.base.UiEffect
import com.ae.alice.presentation.base.UiIntent
import com.ae.alice.presentation.base.UiState

/**
 * ViewModel for ModelsScreen using MVI pattern.
 */
class ModelsViewModel(
    private val carModelRepository: CarModelRepository
) : BaseViewModel<ModelsState, ModelsIntent, ModelsEffect>(ModelsState()) {

    override fun handleIntent(intent: ModelsIntent) {
        when (intent) {
            is ModelsIntent.LoadModels -> loadModels(intent.brandId)
            is ModelsIntent.Search -> onSearch(intent.query)
            is ModelsIntent.ModelClicked -> { /* Handled by UI */ }
        }
    }

    private fun loadModels(brandId: String) {
        launch {
            updateState { copy(isLoading = true, error = null) }
            try {
                val models = carModelRepository.getModelsByBrand(brandId)
                updateState {
                    copy(
                        isLoading = false,
                        models = models,
                        filteredModels = models
                    )
                }
            } catch (e: Exception) {
                updateState {
                    copy(
                        isLoading = false,
                        error = e.message ?: "Failed to load models"
                    )
                }
            }
        }
    }

    private fun onSearch(query: String) {
        updateState { copy(searchQuery = query) }
        filterModels(query)
    }

    private fun filterModels(query: String) {
        val filtered = if (query.isBlank()) {
            currentState.models
        } else {
            currentState.models.filter {
                it.name.contains(query, ignoreCase = true) ||
                it.category?.contains(query, ignoreCase = true) == true
            }
        }
        updateState { copy(filteredModels = filtered) }
    }
}

/**
 * UI State for ModelsScreen.
 */
data class ModelsState(
    val isLoading: Boolean = false,
    val models: List<CarModel> = emptyList(),
    val filteredModels: List<CarModel> = emptyList(),
    val searchQuery: String = "",
    val error: String? = null
) : UiState

/**
 * Intents for ModelsScreen.
 */
sealed interface ModelsIntent : UiIntent {
    data class LoadModels(val brandId: String) : ModelsIntent
    data class Search(val query: String) : ModelsIntent
    data class ModelClicked(val model: CarModel) : ModelsIntent
}

/**
 * Effects for ModelsScreen.
 */
sealed interface ModelsEffect : UiEffect
