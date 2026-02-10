package com.ae.alice.data.fake

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import com.ae.alice.domain.entity.CarModel
import com.ae.alice.domain.repository.CarModelRepository

/**
 * Fake implementation of CarModelRepository for development/testing.
 * Uses picsum.photos for reliable placeholder images.
 */
class FakeCarModelRepository : CarModelRepository {

    private val modelsByBrand = mapOf(
        "1" to listOf(  // BMW
            CarModel(
                id = "bmw-1", name = "3 Series", brandId = "1", brandName = "BMW",
                imageUrl = "https://picsum.photos/seed/bmw3/400/300",
                year = 2024, category = "Sedan", engineType = "Gasoline", transmission = "Automatic",
                horsepower = 255, priceAmount = 45000.0, priceCurrency = "USD",
                description = "The BMW 3 Series is a compact executive car manufactured by BMW since 1975."
            ),
            CarModel(
                id = "bmw-2", name = "5 Series", brandId = "1", brandName = "BMW",
                imageUrl = "https://picsum.photos/seed/bmw5/400/300",
                year = 2024, category = "Sedan", engineType = "Hybrid", transmission = "Automatic",
                horsepower = 335, priceAmount = 58000.0, priceCurrency = "USD",
                description = "The BMW 5 Series is an executive car manufactured by BMW since 1972."
            ),
            CarModel(
                id = "bmw-3", name = "X5", brandId = "1", brandName = "BMW",
                imageUrl = "https://picsum.photos/seed/bmwx5/400/300",
                year = 2024, category = "SUV", engineType = "Gasoline", transmission = "Automatic",
                horsepower = 335, priceAmount = 65000.0, priceCurrency = "USD",
                description = "The BMW X5 is a mid-size luxury SUV produced by BMW."
            )
        ),
        "2" to listOf(  // Mercedes-Benz
            CarModel(
                id = "merc-1", name = "C-Class", brandId = "2", brandName = "Mercedes-Benz",
                imageUrl = "https://picsum.photos/seed/mercc/400/300",
                year = 2024, category = "Sedan", engineType = "Gasoline", transmission = "Automatic",
                horsepower = 255, priceAmount = 48000.0, priceCurrency = "USD",
                description = "The Mercedes-Benz C-Class is a line of compact executive cars."
            ),
            CarModel(
                id = "merc-2", name = "E-Class", brandId = "2", brandName = "Mercedes-Benz",
                imageUrl = "https://picsum.photos/seed/merce/400/300",
                year = 2024, category = "Sedan", engineType = "Hybrid", transmission = "Automatic",
                horsepower = 375, priceAmount = 62000.0, priceCurrency = "USD",
                description = "The Mercedes-Benz E-Class is a range of executive cars."
            ),
            CarModel(
                id = "merc-3", name = "S-Class", brandId = "2", brandName = "Mercedes-Benz",
                imageUrl = "https://picsum.photos/seed/mercs/400/300",
                year = 2024, category = "Luxury", engineType = "Hybrid", transmission = "Automatic",
                horsepower = 496, priceAmount = 115000.0, priceCurrency = "USD",
                description = "The Mercedes-Benz S-Class is a series of full-size luxury sedans."
            )
        ),
        "3" to listOf(  // Audi
            CarModel(
                id = "audi-1", name = "A4", brandId = "3", brandName = "Audi",
                imageUrl = "https://picsum.photos/seed/audia4/400/300",
                year = 2024, category = "Sedan", engineType = "Gasoline", transmission = "Automatic",
                horsepower = 201, priceAmount = 43000.0, priceCurrency = "USD",
                description = "The Audi A4 is a line of compact executive cars."
            ),
            CarModel(
                id = "audi-2", name = "Q5", brandId = "3", brandName = "Audi",
                imageUrl = "https://picsum.photos/seed/audiq5/400/300",
                year = 2024, category = "SUV", engineType = "Gasoline", transmission = "Automatic",
                horsepower = 261, priceAmount = 52000.0, priceCurrency = "USD",
                description = "The Audi Q5 is a compact luxury crossover SUV."
            )
        ),
        "4" to listOf(  // Toyota
            CarModel(
                id = "toyota-1", name = "Camry", brandId = "4", brandName = "Toyota",
                imageUrl = "https://picsum.photos/seed/camry/400/300",
                year = 2024, category = "Sedan", engineType = "Hybrid", transmission = "Automatic",
                horsepower = 225, priceAmount = 28000.0, priceCurrency = "USD",
                description = "The Toyota Camry is an automobile sold internationally by the Japanese manufacturer Toyota."
            ),
            CarModel(
                id = "toyota-2", name = "RAV4", brandId = "4", brandName = "Toyota",
                imageUrl = "https://picsum.photos/seed/rav4/400/300",
                year = 2024, category = "SUV", engineType = "Hybrid", transmission = "Automatic",
                horsepower = 219, priceAmount = 32000.0, priceCurrency = "USD",
                description = "The Toyota RAV4 is a compact crossover SUV produced by the Japanese automobile manufacturer Toyota."
            )
        ),
        "5" to listOf(  // Honda
            CarModel(
                id = "honda-1", name = "Accord", brandId = "5", brandName = "Honda",
                imageUrl = "https://picsum.photos/seed/accord/400/300",
                year = 2024, category = "Sedan", engineType = "Hybrid", transmission = "Automatic",
                horsepower = 252, priceAmount = 32000.0, priceCurrency = "USD",
                description = "The Honda Accord is a series of automobiles manufactured by Honda."
            ),
            CarModel(
                id = "honda-2", name = "CR-V", brandId = "5", brandName = "Honda",
                imageUrl = "https://picsum.photos/seed/crv/400/300",
                year = 2024, category = "SUV", engineType = "Hybrid", transmission = "Automatic",
                horsepower = 204, priceAmount = 35000.0, priceCurrency = "USD",
                description = "The Honda CR-V is a compact crossover SUV manufactured by Honda."
            )
        ),
        "6" to listOf(  // Ford
            CarModel(
                id = "ford-1", name = "Mustang", brandId = "6", brandName = "Ford",
                imageUrl = "https://picsum.photos/seed/mustang/400/300",
                year = 2024, category = "Sports", engineType = "Gasoline", transmission = "Manual",
                horsepower = 450, priceAmount = 55000.0, priceCurrency = "USD",
                description = "The Ford Mustang is a series of American automobiles manufactured by Ford."
            ),
            CarModel(
                id = "ford-2", name = "F-150", brandId = "6", brandName = "Ford",
                imageUrl = "https://picsum.photos/seed/f150/400/300",
                year = 2024, category = "Truck", engineType = "Gasoline", transmission = "Automatic",
                horsepower = 400, priceAmount = 48000.0, priceCurrency = "USD",
                description = "The Ford F-Series is a series of trucks marketed and manufactured by Ford."
            )
        ),
        "7" to listOf(  // Porsche
            CarModel(
                id = "porsche-1", name = "911", brandId = "7", brandName = "Porsche",
                imageUrl = "https://picsum.photos/seed/porsche911/400/300",
                year = 2024, category = "Sports", engineType = "Gasoline", transmission = "Automatic",
                horsepower = 443, priceAmount = 115000.0, priceCurrency = "USD",
                description = "The Porsche 911 is a two-door, 2+2 high performance rear-engined sports car."
            ),
            CarModel(
                id = "porsche-2", name = "Cayenne", brandId = "7", brandName = "Porsche",
                imageUrl = "https://picsum.photos/seed/cayenne/400/300",
                year = 2024, category = "SUV", engineType = "Hybrid", transmission = "Automatic",
                horsepower = 541, priceAmount = 95000.0, priceCurrency = "USD",
                description = "The Porsche Cayenne is a mid-size luxury crossover sport utility vehicle."
            )
        ),
        "8" to listOf(  // Ferrari
            CarModel(
                id = "ferrari-1", name = "Roma", brandId = "8", brandName = "Ferrari",
                imageUrl = "https://picsum.photos/seed/ferrarroma/400/300",
                year = 2024, category = "Sports", engineType = "Gasoline", transmission = "Automatic",
                horsepower = 612, priceAmount = 250000.0, priceCurrency = "USD",
                description = "The Ferrari Roma is a grand touring coupe produced by Ferrari."
            )
        ),
        "9" to listOf(  // Lamborghini
            CarModel(
                id = "lambo-1", name = "Huracán", brandId = "9", brandName = "Lamborghini",
                imageUrl = "https://picsum.photos/seed/huracan/400/300",
                year = 2024, category = "Sports", engineType = "Gasoline", transmission = "Automatic",
                horsepower = 631, priceAmount = 275000.0, priceCurrency = "USD",
                description = "The Lamborghini Huracán is a sports car built by Lamborghini."
            )
        ),
        "10" to listOf(  // Tesla
            CarModel(
                id = "tesla-1", name = "Model 3", brandId = "10", brandName = "Tesla",
                imageUrl = "https://picsum.photos/seed/model3/400/300",
                year = 2024, category = "Electric", engineType = "Electric", transmission = "Automatic",
                horsepower = 283, priceAmount = 42000.0, priceCurrency = "USD",
                description = "The Tesla Model 3 is a battery electric compact executive sedan."
            ),
            CarModel(
                id = "tesla-2", name = "Model Y", brandId = "10", brandName = "Tesla",
                imageUrl = "https://picsum.photos/seed/modely/400/300",
                year = 2024, category = "Electric", engineType = "Electric", transmission = "Automatic",
                horsepower = 384, priceAmount = 52000.0, priceCurrency = "USD",
                description = "The Tesla Model Y is a battery electric compact crossover utility vehicle."
            )
        )
    )
    
    private val allModels: List<CarModel> by lazy {
        modelsByBrand.values.flatten()
    }
    
    override suspend fun getModelsByBrand(brandId: String): List<CarModel> {
        return modelsByBrand[brandId] ?: emptyList()
    }
    
    override suspend fun getModelById(id: String): CarModel? {
        return allModels.find { it.id == id }
    }
    
    override suspend fun searchModels(query: String): List<CarModel> {
        if (query.isBlank()) return allModels
        return allModels.filter {
            it.name.contains(query, ignoreCase = true) ||
            it.brandName.contains(query, ignoreCase = true) ||
            it.category?.contains(query, ignoreCase = true) == true
        }
    }
    
    override fun observeModelsByBrand(brandId: String): Flow<List<CarModel>> {
        return flowOf(modelsByBrand[brandId] ?: emptyList())
    }
}
