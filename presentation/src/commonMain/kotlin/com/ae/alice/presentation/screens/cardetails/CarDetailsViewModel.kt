package com.ae.alice.presentation.screens.cardetails

import com.ae.alice.domain.repository.CarModelRepository
import com.ae.alice.presentation.base.BaseViewModel

/**
 * ViewModel for CarDetailsScreen using MVI pattern.
 */
class CarDetailsViewModel(
    private val carModelRepository: CarModelRepository
) : BaseViewModel<CarDetailsState, CarDetailsIntent, CarDetailsEffect>(CarDetailsState()) {

    private var currentModelId: String? = null

    override fun handleIntent(intent: CarDetailsIntent) {
        when (intent) {
            is CarDetailsIntent.LoadModel -> {
                currentModelId = intent.modelId
                loadModel(intent.modelId)
            }
            is CarDetailsIntent.GetCar -> getCar()
            is CarDetailsIntent.Retry -> currentModelId?.let { loadModel(it) }
        }
    }

    private fun getCar() {
        val model = currentState.model ?: return
        emitEffect(CarDetailsEffect.NavigateToGetCar(model.id, model.name))
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
