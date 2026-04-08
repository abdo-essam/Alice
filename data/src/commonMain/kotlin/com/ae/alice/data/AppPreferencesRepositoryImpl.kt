package com.ae.alice.data

import com.ae.alice.domain.entity.Country
import com.ae.alice.domain.repository.AppPreferencesRepository
import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppPreferencesRepositoryImpl(
    private val settings: Settings
) : AppPreferencesRepository {

    private val countryKey = "SELECTED_COUNTRY_CODE"

    private val _selectedCountry = MutableStateFlow(getInitialCountry())
    override val selectedCountry: StateFlow<Country> = _selectedCountry.asStateFlow()

    private fun getInitialCountry(): Country {
        val code = settings.getString(countryKey, Country.default().name)
        return Country.entries.find { it.name == code } ?: Country.default()
    }

    override suspend fun saveSelectedCountry(country: Country) {
        settings.putString(countryKey, country.name)
        _selectedCountry.value = country
    }
}
