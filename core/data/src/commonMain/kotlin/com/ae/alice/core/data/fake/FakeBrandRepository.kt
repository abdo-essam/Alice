package com.ae.alice.core.data.fake

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import com.ae.alice.core.domain.entity.Brand
import com.ae.alice.core.domain.repository.BrandRepository

/**
 * Fake implementation of BrandRepository for development/testing.
 */
class FakeBrandRepository : BrandRepository {
    
    private val brands = listOf(
        Brand(
            id = "1",
            name = "BMW",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/4/44/BMW.svg",
            country = "Germany",
            foundedYear = 1916,
            description = "Bayerische Motoren Werke AG, commonly known as BMW, is a German multinational company which produces luxury vehicles and motorcycles.",
            modelsCount = 12
        ),
        Brand(
            id = "2",
            name = "Mercedes-Benz",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/9/90/Mercedes-Logo.svg",
            country = "Germany",
            foundedYear = 1926,
            description = "Mercedes-Benz is a German luxury and commercial vehicle automotive brand established in 1926.",
            modelsCount = 15
        ),
        Brand(
            id = "3",
            name = "Audi",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/9/92/Audi-Logo_2016.svg",
            country = "Germany",
            foundedYear = 1909,
            description = "Audi AG is a German automotive manufacturer of luxury vehicles.",
            modelsCount = 10
        ),
        Brand(
            id = "4",
            name = "Toyota",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/9/9d/Toyota_carlogo.svg",
            country = "Japan",
            foundedYear = 1937,
            description = "Toyota Motor Corporation is a Japanese multinational automotive manufacturer.",
            modelsCount = 18
        ),
        Brand(
            id = "5",
            name = "Honda",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/3/38/Honda.svg",
            country = "Japan",
            foundedYear = 1948,
            description = "Honda Motor Company, Ltd. is a Japanese public multinational conglomerate manufacturer of automobiles.",
            modelsCount = 14
        ),
        Brand(
            id = "6",
            name = "Ford",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3e/Ford_logo_flat.svg",
            country = "USA",
            foundedYear = 1903,
            description = "Ford Motor Company is an American multinational automobile manufacturer.",
            modelsCount = 16
        ),
        Brand(
            id = "7",
            name = "Porsche",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/5/5e/Porsche_logo.svg",
            country = "Germany",
            foundedYear = 1931,
            description = "Dr. Ing. h.c. F. Porsche AG is a German automobile manufacturer specializing in sports cars, SUVs and sedans.",
            modelsCount = 8
        ),
        Brand(
            id = "8",
            name = "Ferrari",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/d/d7/Ferrari-Logo.svg",
            country = "Italy",
            foundedYear = 1939,
            description = "Ferrari S.p.A. is an Italian luxury sports car manufacturer.",
            modelsCount = 6
        ),
        Brand(
            id = "9",
            name = "Lamborghini",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/9/9e/Lamborghini_Logo.svg",
            country = "Italy",
            foundedYear = 1963,
            description = "Automobili Lamborghini S.p.A. is an Italian manufacturer of luxury sports cars and SUVs.",
            modelsCount = 5
        ),
        Brand(
            id = "10",
            name = "Tesla",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/e/e8/Tesla_logo.png",
            country = "USA",
            foundedYear = 2003,
            description = "Tesla, Inc. is an American electric vehicle and clean energy company.",
            modelsCount = 4
        )
    )
    
    override suspend fun getBrands(): List<Brand> = brands
    
    override suspend fun getBrandById(id: String): Brand? = brands.find { it.id == id }
    
    override suspend fun searchBrands(query: String): List<Brand> {
        if (query.isBlank()) return brands
        return brands.filter { 
            it.name.contains(query, ignoreCase = true) ||
            it.country?.contains(query, ignoreCase = true) == true
        }
    }
    
    override fun observeBrands(): Flow<List<Brand>> = flowOf(brands)
}
