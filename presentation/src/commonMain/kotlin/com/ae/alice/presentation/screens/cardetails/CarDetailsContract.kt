package com.ae.alice.presentation.screens.cardetails

import com.ae.alice.domain.entity.CarModel
import com.ae.alice.presentation.base.UiEffect
import com.ae.alice.presentation.base.UiIntent
import com.ae.alice.presentation.base.UiState

/**
 * UI State for CarDetailsScreen.
 */
data class CarDetailsState(
    val isLoading: Boolean = false,
    val model: CarModel? = null,
    val error: String? = null
) : UiState {
    val isContentReady: Boolean get() = model != null && !isLoading && error == null
    val hasError: Boolean get() = error != null && !isLoading
}

/**
 * Intents for CarDetailsScreen.
 */
sealed interface CarDetailsIntent : UiIntent {
    data class LoadModel(val modelId: String) : CarDetailsIntent
    data object GetCar : CarDetailsIntent
    data object Retry : CarDetailsIntent
}

/**
 * Effects for CarDetailsScreen.
 */
sealed interface CarDetailsEffect : UiEffect {
    data class NavigateToGetCar(val modelId: String, val modelName: String) : CarDetailsEffect
}

