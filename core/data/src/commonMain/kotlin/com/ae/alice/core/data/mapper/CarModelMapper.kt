package com.ae.alice.core.data.mapper

import com.ae.alice.core.data.dto.CarModelDto
import com.ae.alice.core.data.dto.ModelsResponseDto
import com.ae.alice.core.domain.entity.CarCategory
import com.ae.alice.core.domain.entity.CarModel
import com.ae.alice.core.domain.entity.CarModelsList
import com.ae.alice.core.domain.entity.EngineType
import com.ae.alice.core.domain.entity.Price
import com.ae.alice.core.domain.entity.TransmissionType

/**
 * Mapper for CarModel DTOs to Domain entities
 */
object CarModelMapper {
    
    fun CarModelDto.toDomain(isFavorite: Boolean = false): CarModel {
        return CarModel(
            id = id,
            name = name,
            brandId = brandId,
            brandName = brandName,
            imageUrl = imageUrl,
            year = year,
            category = category?.toCarCategory(),
            engineType = engineType?.toEngineType(),
            transmission = transmission?.toTransmissionType(),
            horsepower = horsepower,
            price = price?.let { Price(it, currency) },
            description = description,
            isFavorite = isFavorite
        )
    }
    
    fun ModelsResponseDto.toDomain(favoriteIds: Set<String> = emptySet()): CarModelsList {
        return CarModelsList(
            models = data.map { it.toDomain(favoriteIds.contains(it.id)) },
            totalCount = totalCount,
            page = page,
            pageSize = pageSize
        )
    }
    
    fun List<CarModelDto>.toDomainList(favoriteIds: Set<String> = emptySet()): List<CarModel> {
        return map { it.toDomain(favoriteIds.contains(it.id)) }
    }
    
    private fun String.toCarCategory(): CarCategory? {
        return try {
            CarCategory.valueOf(uppercase())
        } catch (e: Exception) {
            null
        }
    }
    
    private fun String.toEngineType(): EngineType? {
        return try {
            EngineType.valueOf(uppercase().replace("-", "_").replace(" ", "_"))
        } catch (e: Exception) {
            null
        }
    }
    
    private fun String.toTransmissionType(): TransmissionType? {
        return try {
            TransmissionType.valueOf(uppercase())
        } catch (e: Exception) {
            null
        }
    }
}
