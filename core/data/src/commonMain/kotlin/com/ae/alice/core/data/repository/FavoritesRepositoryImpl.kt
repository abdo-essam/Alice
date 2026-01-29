package com.ae.alice.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import com.ae.alice.core.common.base.safeDbCall
import com.ae.alice.core.common.result.Result
import com.ae.alice.core.data.dto.CarModelDto
import com.ae.alice.core.data.mapper.CarModelMapper.toDomain
import com.ae.alice.core.domain.entity.CarModel
import com.ae.alice.core.domain.repository.FavoritesRepository

/**
 * Implementation of FavoritesRepository using DataStore
 */
class FavoritesRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
    private val json: Json
) : FavoritesRepository {
    
    companion object {
        private val FAVORITE_IDS_KEY = stringSetPreferencesKey("favorite_ids")
        private const val FAVORITE_MODEL_PREFIX = "favorite_model_"
    }
    
    override suspend fun getFavoriteIds(): Result<Set<String>> = safeDbCall {
        dataStore.data.first()[FAVORITE_IDS_KEY] ?: emptySet()
    }
    
    override suspend fun getFavorites(): Result<List<CarModel>> = safeDbCall {
        val prefs = dataStore.data.first()
        val favoriteIds = prefs[FAVORITE_IDS_KEY] ?: emptySet()
        
        favoriteIds.mapNotNull { id ->
            val key = stringPreferencesKey("$FAVORITE_MODEL_PREFIX$id")
            prefs[key]?.let { jsonString ->
                try {
                    json.decodeFromString<CarModelDto>(jsonString).toDomain(isFavorite = true)
                } catch (e: Exception) {
                    null
                }
            }
        }
    }
    
    override suspend fun isFavorite(modelId: String): Result<Boolean> = safeDbCall {
        val ids = dataStore.data.first()[FAVORITE_IDS_KEY] ?: emptySet()
        modelId in ids
    }
    
    override suspend fun addToFavorites(model: CarModel): Result<Unit> = safeDbCall {
        dataStore.edit { prefs ->
            val currentIds = prefs[FAVORITE_IDS_KEY] ?: emptySet()
            prefs[FAVORITE_IDS_KEY] = currentIds + model.id
            
            // Store the model data for offline access
            val key = stringPreferencesKey("$FAVORITE_MODEL_PREFIX${model.id}")
            val dto = CarModelDto(
                id = model.id,
                name = model.name,
                brandId = model.brandId,
                brandName = model.brandName,
                imageUrl = model.imageUrl,
                year = model.year,
                category = model.category?.name,
                engineType = model.engineType?.name,
                transmission = model.transmission?.name,
                horsepower = model.horsepower,
                price = model.price?.amount,
                currency = model.price?.currency ?: "USD",
                description = model.description
            )
            prefs[key] = json.encodeToString(dto)
        }
    }
    
    override suspend fun removeFromFavorites(modelId: String): Result<Unit> = safeDbCall {
        dataStore.edit { prefs ->
            val currentIds = prefs[FAVORITE_IDS_KEY] ?: emptySet()
            prefs[FAVORITE_IDS_KEY] = currentIds - modelId
            
            // Remove the stored model data
            val key = stringPreferencesKey("$FAVORITE_MODEL_PREFIX$modelId")
            prefs.remove(key)
        }
    }
    
    override suspend fun toggleFavorite(model: CarModel): Result<Boolean> = safeDbCall {
        val currentIds = dataStore.data.first()[FAVORITE_IDS_KEY] ?: emptySet()
        val isFavorite = model.id in currentIds
        
        if (isFavorite) {
            removeFromFavorites(model.id)
            false
        } else {
            addToFavorites(model)
            true
        }
    }
    
    override fun observeFavoriteIds(): Flow<Set<String>> {
        return dataStore.data.map { prefs ->
            prefs[FAVORITE_IDS_KEY] ?: emptySet()
        }
    }
    
    override fun observeFavorites(): Flow<List<CarModel>> {
        return dataStore.data.map { prefs ->
            val favoriteIds = prefs[FAVORITE_IDS_KEY] ?: emptySet()
            favoriteIds.mapNotNull { id ->
                val key = stringPreferencesKey("$FAVORITE_MODEL_PREFIX$id")
                prefs[key]?.let { jsonString ->
                    try {
                        json.decodeFromString<CarModelDto>(jsonString).toDomain(isFavorite = true)
                    } catch (e: Exception) {
                        null
                    }
                }
            }
        }
    }
    
    override suspend fun clearAllFavorites(): Result<Unit> = safeDbCall {
        dataStore.edit { prefs ->
            val favoriteIds = prefs[FAVORITE_IDS_KEY] ?: emptySet()
            favoriteIds.forEach { id ->
                val key = stringPreferencesKey("$FAVORITE_MODEL_PREFIX$id")
                prefs.remove(key)
            }
            prefs.remove(FAVORITE_IDS_KEY)
        }
    }
}
