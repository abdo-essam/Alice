package com.ae.alice.presentation.screens.models

import com.ae.alice.domain.entity.CarModel
import com.ae.alice.presentation.base.UiEffect
import com.ae.alice.presentation.base.UiIntent
import com.ae.alice.presentation.base.UiState

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
