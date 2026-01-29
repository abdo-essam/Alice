package com.ae.alice.core.data.mapper

import com.ae.alice.core.data.dto.BrandDto
import com.ae.alice.core.data.dto.BrandsResponseDto
import com.ae.alice.core.domain.entity.Brand
import com.ae.alice.core.domain.entity.BrandsList

/**
 * Mapper for Brand DTOs to Domain entities
 */
object BrandMapper {
    
    fun BrandDto.toDomain(): Brand {
        return Brand(
            id = id,
            name = name,
            logoUrl = logoUrl,
            country = country,
            foundedYear = foundedYear,
            description = description,
            modelsCount = modelsCount
        )
    }
    
    fun BrandsResponseDto.toDomain(): BrandsList {
        return BrandsList(
            brands = data.map { it.toDomain() },
            totalCount = totalCount,
            page = page,
            pageSize = pageSize
        )
    }
    
    fun List<BrandDto>.toDomainList(): List<Brand> {
        return map { it.toDomain() }
    }
}
