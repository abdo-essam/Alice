package com.ae.alice.domain.repository

import com.ae.alice.domain.entity.City
import com.ae.alice.domain.entity.Place
import com.ae.alice.domain.entity.ServiceCategory

import com.ae.alice.domain.entity.Country

interface PlaceRepository {
    suspend fun getCountries(): List<Country>
    suspend fun getCategories(): List<ServiceCategory>
    suspend fun getCities(countryCode: String? = null): List<City>
    suspend fun getPlacesByCategory(categoryId: String, city: String = ""): List<Place>
    suspend fun searchPlaces(query: String, city: String = ""): List<Place>
    suspend fun savePlace(placeId: String)
    suspend fun unsavePlace(placeId: String)
}
