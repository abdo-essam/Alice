package com.ae.alice.domain.repository

import com.ae.alice.domain.entity.Country
import kotlinx.coroutines.flow.Flow

interface AppPreferencesRepository {
    val selectedCountry: Flow<Country?>
    suspend fun saveSelectedCountry(country: Country)
}
