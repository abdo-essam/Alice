package com.ae.alice.data.fake

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import com.ae.alice.domain.entity.Brand
import com.ae.alice.domain.repository.BrandRepository

/**
 * Fake implementation of BrandRepository for development/testing.
 * Uses PNG image URLs that work reliably with Coil image loading.
 */
class FakeBrandRepository : BrandRepository {
    
    private val brands = listOf(
        Brand(
            id = "1",
            name = "BMW",
            logoUrl = "https://www.carlogos.org/car-logos/bmw-logo-2020-blue-white.png",
            country = "Germany",
            foundedYear = 1916,
            description = "Bayerische Motoren Werke AG, commonly known as BMW, is a German multinational company which produces luxury vehicles and motorcycles.",
            modelsCount = 12
        ),
        Brand(
            id = "2",
            name = "Mercedes-Benz",
            logoUrl = "https://www.carlogos.org/logo/Mercedes-Benz-logo-2011-640x369.jpg",
            country = "Germany",
            foundedYear = 1926,
            description = "Mercedes-Benz is a German luxury and commercial vehicle automotive brand established in 1926.",
            modelsCount = 15
        ),
        Brand(
            id = "3",
            name = "Audi",
            logoUrl = "https://www.carlogos.org/car-logos/audi-logo-2016.png",
            country = "Germany",
            foundedYear = 1909,
            description = "Audi AG is a German automotive manufacturer of luxury vehicles.",
            modelsCount = 10
        ),
        Brand(
            id = "4",
            name = "Toyota",
            logoUrl = "https://www.carlogos.org/car-logos/toyota-logo-2020-europe-640.png",
            country = "Japan",
            foundedYear = 1937,
            description = "Toyota Motor Corporation is a Japanese multinational automotive manufacturer.",
            modelsCount = 18
        ),
        Brand(
            id = "5",
            name = "Honda",
            logoUrl = "https://www.carlogos.org/car-logos/honda-logo-2000.png",
            country = "Japan",
            foundedYear = 1948,
            description = "Honda Motor Company, Ltd. is a Japanese public multinational conglomerate manufacturer of automobiles.",
            modelsCount = 14
        ),
        Brand(
            id = "6",
            name = "Ford",
            logoUrl = "https://www.carlogos.org/car-logos/ford-logo-2017.png",
            country = "USA",
            foundedYear = 1903,
            description = "Ford Motor Company is an American multinational automobile manufacturer.",
            modelsCount = 16
        ),
        Brand(
            id = "7",
            name = "Porsche",
            logoUrl = "https://www.carlogos.org/car-logos/porsche-logo-2014.png",
            country = "Germany",
            foundedYear = 1931,
            description = "Dr. Ing. h.c. F. Porsche AG is a German automobile manufacturer specializing in sports cars, SUVs and sedans.",
            modelsCount = 8
        ),
        Brand(
            id = "8",
            name = "Ferrari",
            logoUrl = "https://www.carlogos.org/car-logos/ferrari-logo-2002.png",
            country = "Italy",
            foundedYear = 1939,
            description = "Ferrari S.p.A. is an Italian luxury sports car manufacturer.",
            modelsCount = 6
        ),
        Brand(
            id = "9",
            name = "Lamborghini",
            logoUrl = "https://www.carlogos.org/car-logos/lamborghini-logo-1998.png",
            country = "Italy",
            foundedYear = 1963,
            description = "Automobili Lamborghini S.p.A. is an Italian manufacturer of luxury sports cars and SUVs.",
            modelsCount = 5
        ),
        Brand(
            id = "10",
            name = "Tesla",
            logoUrl = "https://www.carlogos.org/car-logos/tesla-logo-2003.png",
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
