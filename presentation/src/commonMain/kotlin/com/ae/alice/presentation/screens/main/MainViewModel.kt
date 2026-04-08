package com.ae.alice.presentation.screens.main

import com.ae.alice.domain.entity.Country
import com.ae.alice.domain.repository.PlaceRepository
import com.ae.alice.domain.repository.AppPreferencesRepository
import com.ae.alice.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.collectLatest

import com.ae.alice.presentation.base.UiState
import com.ae.alice.presentation.base.UiIntent
import com.ae.alice.presentation.base.UiEffect

data class MainState(
    val selectedCountry: Country? = null,
    val countries: List<Country> = emptyList(),
    val showCountryPicker: Boolean = false
) : UiState

sealed interface MainIntent : UiIntent {
    data object ShowCountryPicker : MainIntent
    data object HideCountryPicker : MainIntent
    data class SelectCountry(val country: Country) : MainIntent
}

sealed interface MainEffect : UiEffect

class MainViewModel(
    private val appPreferencesRepository: AppPreferencesRepository,
    private val placeRepository: PlaceRepository
) : BaseViewModel<MainState, MainIntent, MainEffect>(MainState()) {

    init {
        loadData()
        observeCountry()
    }

    private fun loadData() {
        tryExecute(
            call = { placeRepository.getCountries() },
            onSuccess = { countries ->
                updateState { copy(countries = countries) }
            },
            onError = {}
        )
    }

    private fun observeCountry() {
        tryExecute(
            call = {
                appPreferencesRepository.selectedCountry.collectLatest { country ->
                    updateState { copy(selectedCountry = country) }
                }
            },
            onSuccess = {},
            onError = {}
        )
    }

    override fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.ShowCountryPicker -> updateState { copy(showCountryPicker = true) }
            is MainIntent.HideCountryPicker -> updateState { copy(showCountryPicker = false) }
            is MainIntent.SelectCountry -> {
                updateState { copy(showCountryPicker = false) }
                tryExecute(
                    call = { appPreferencesRepository.saveSelectedCountry(intent.country) },
                    onSuccess = {},
                    onError = {}
                )
            }
        }
    }
}
