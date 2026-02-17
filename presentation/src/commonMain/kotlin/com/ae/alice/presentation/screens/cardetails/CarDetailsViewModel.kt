package com.ae.alice.presentation.screens.cardetails

import com.ae.alice.domain.repository.CarModelRepository
import com.ae.alice.presentation.base.BaseViewModel

/**
 * ViewModel for CarDetailsScreen using MVI pattern.
 */
class CarDetailsViewModel(
    private val carModelRepository: CarModelRepository
) : BaseViewModel<CarDetailsState, CarDetailsIntent, CarDetailsEffect>(CarDetailsState()) {

    override fun handleIntent(intent: CarDetailsIntent) {
        when (intent) {
            is CarDetailsIntent.LoadModel -> loadModel(intent.modelId)
        }
    }

    private fun loadModel(modelId: String) {
        updateState { copy(isLoading = true, error = null) }
        tryExecute(
            call = { carModelRepository.getModelById(modelId) },
            onSuccess = { model ->
                updateState {
                    copy(
                        isLoading = false,
                        model = model
                    )
                }
            },
            onError = { e ->
                updateState {
                    copy(
                        isLoading = false,
                        error = e.message ?: "Failed to load car details"
                    )
                }
            }
        )
    }
}
