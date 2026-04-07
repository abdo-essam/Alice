package com.ae.alice.domain.repository

import com.ae.alice.domain.entity.Location
import com.ae.alice.domain.entity.Place
import com.ae.alice.domain.entity.ServiceCategory

interface PlaceRepository {
    suspend fun getCategories(): List<ServiceCategory>
    suspend fun getLocations(): List<Location>
    suspend fun getPlacesByCategory(categoryId: String, location: String = ""): List<Place>
    suspend fun searchPlaces(query: String, location: String = ""): List<Place>
    suspend fun savePlace(placeId: String)
    suspend fun unsavePlace(placeId: String)
}
