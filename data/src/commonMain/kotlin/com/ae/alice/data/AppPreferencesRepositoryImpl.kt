package com.ae.alice.data

import com.ae.alice.domain.entity.Country
import com.ae.alice.domain.repository.AppPreferencesRepository
import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import com.ae.alice.domain.repository.PlaceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class AppPreferencesRepositoryImpl(
    private val settings: Settings,
    private val placeRepository: PlaceRepository
) : AppPreferencesRepository {

    private val countryKey = "SELECTED_COUNTRY_CODE"

    private val _selectedCountry = MutableStateFlow(Country.default())
    override val selectedCountry: StateFlow<Country> = _selectedCountry.asStateFlow()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val code = settings.getString(countryKey, Country.default().countryCodeName)
            val country = placeRepository.getCountries().find { it.countryCodeName == code } ?: Country.default()
            _selectedCountry.value = country
        }
    }

    override suspend fun saveSelectedCountry(country: Country) {
        settings.putString(countryKey, country.countryCodeName)
        _selectedCountry.value = country
    }
}
