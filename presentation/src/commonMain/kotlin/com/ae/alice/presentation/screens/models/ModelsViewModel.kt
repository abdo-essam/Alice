package com.ae.alice.presentation.screens.models

import com.ae.alice.domain.repository.CarModelRepository
import com.ae.alice.presentation.base.BaseViewModel

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
        updateState { copy(isLoading = true, error = null) }
        tryExecute(
            call = { carModelRepository.getModelsByBrand(brandId) },
            onSuccess = { models ->
                updateState {
                    copy(
                        isLoading = false,
                        models = models,
                        filteredModels = models
                    )
                }
            },
            onError = { e ->
                updateState {
                    copy(
                        isLoading = false,
                        error = e.message ?: "Failed to load models"
                    )
                }
            }
        )
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
