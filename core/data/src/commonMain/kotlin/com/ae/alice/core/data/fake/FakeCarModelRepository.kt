package com.ae.alice.core.data.fake

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import com.ae.alice.core.domain.entity.CarModel
import com.ae.alice.core.domain.repository.CarModelRepository

/**
 * Fake implementation of CarModelRepository for development/testing.
 */
class FakeCarModelRepository : CarModelRepository {

    //https://www.bmw.cc/content/dam/bmw/common/all-models/m-series/m5-sedan/2021/Navigation/bmw-5-series-sedan-m-modelfinder-stage2-890x501.png

    private val modelsByBrand = mapOf(
        "1" to listOf(  // BMW
            CarModel(
                id = "bmw-1", name = "3 Series", brandId = "1", brandName = "BMW",
                imageUrl = "https://www.bmw.com/content/dam/bmw/common/all-models/3-series/sedan/2022/navigation/bmw-3-series-sedan-lci-modelfinder.png",
                year = 2024, category = "Sedan", engineType = "Gasoline", transmission = "Automatic",
                horsepower = 255, priceAmount = 45000.0, priceCurrency = "USD",
                description = "The BMW 3 Series is a compact executive car manufactured by BMW since 1975."
            ),
            CarModel(
                id = "bmw-2", name = "5 Series", brandId = "1", brandName = "BMW",
                imageUrl = "https://www.bmw.com/content/dam/bmw/common/all-models/5-series/sedan/2023/navigation/bmw-5-series-sedan-modelfinder.png",
                year = 2024, category = "Sedan", engineType = "Hybrid", transmission = "Automatic",
                horsepower = 335, priceAmount = 58000.0, priceCurrency = "USD",
                description = "The BMW 5 Series is an executive car manufactured by BMW since 1972."
            ),
            CarModel(
                id = "bmw-3", name = "X5", brandId = "1", brandName = "BMW",
                imageUrl = "https://www.bmw.com/content/dam/bmw/common/all-models/x-series/x5/2023/navigation/bmw-x5-modelfinder.png",
                year = 2024, category = "SUV", engineType = "Gasoline", transmission = "Automatic",
                horsepower = 335, priceAmount = 65000.0, priceCurrency = "USD",
                description = "The BMW X5 is a mid-size luxury SUV produced by BMW."
            )
        ),
        "2" to listOf(  // Mercedes-Benz
            CarModel(
                id = "merc-1", name = "C-Class", brandId = "2", brandName = "Mercedes-Benz",
                imageUrl = "https://www.mercedes-benz.com/content/dam/mercedes-benz/vehicles/passenger-cars/c-class/c-class-sedan.png",
                year = 2024, category = "Sedan", engineType = "Gasoline", transmission = "Automatic",
                horsepower = 255, priceAmount = 48000.0, priceCurrency = "USD",
                description = "The Mercedes-Benz C-Class is a line of compact executive cars."
            ),
            CarModel(
                id = "merc-2", name = "E-Class", brandId = "2", brandName = "Mercedes-Benz",
                imageUrl = "https://www.mercedes-benz.com/content/dam/mercedes-benz/vehicles/passenger-cars/e-class/e-class-sedan.png",
                year = 2024, category = "Sedan", engineType = "Hybrid", transmission = "Automatic",
                horsepower = 375, priceAmount = 62000.0, priceCurrency = "USD",
                description = "The Mercedes-Benz E-Class is a range of executive cars."
            ),
            CarModel(
                id = "merc-3", name = "S-Class", brandId = "2", brandName = "Mercedes-Benz",
                imageUrl = "https://www.mercedes-benz.com/content/dam/mercedes-benz/vehicles/passenger-cars/s-class/s-class-sedan.png",
                year = 2024, category = "Luxury", engineType = "Hybrid", transmission = "Automatic",
                horsepower = 496, priceAmount = 115000.0, priceCurrency = "USD",
                description = "The Mercedes-Benz S-Class is a series of full-size luxury sedans."
            )
        ),
        "3" to listOf(  // Audi
            CarModel(
                id = "audi-1", name = "A4", brandId = "3", brandName = "Audi",
                imageUrl = "https://www.audi.com/content/dam/audi/production/models/a4/a4-sedan.png",
                year = 2024, category = "Sedan", engineType = "Gasoline", transmission = "Automatic",
                horsepower = 201, priceAmount = 43000.0, priceCurrency = "USD",
                description = "The Audi A4 is a line of compact executive cars."
            ),
            CarModel(
                id = "audi-2", name = "Q5", brandId = "3", brandName = "Audi",
                imageUrl = "https://www.audi.com/content/dam/audi/production/models/q5/q5-suv.png",
                year = 2024, category = "SUV", engineType = "Gasoline", transmission = "Automatic",
                horsepower = 261, priceAmount = 52000.0, priceCurrency = "USD",
                description = "The Audi Q5 is a compact luxury crossover SUV."
            )
        ),
        "4" to listOf(  // Toyota
            CarModel(
                id = "toyota-1", name = "Camry", brandId = "4", brandName = "Toyota",
                imageUrl = "https://www.toyota.com/content/dam/toyota/vehicles/camry/camry.png",
                year = 2024, category = "Sedan", engineType = "Hybrid", transmission = "Automatic",
                horsepower = 225, priceAmount = 28000.0, priceCurrency = "USD",
                description = "The Toyota Camry is an automobile sold internationally by the Japanese manufacturer Toyota."
            ),
            CarModel(
                id = "toyota-2", name = "RAV4", brandId = "4", brandName = "Toyota",
                imageUrl = "https://www.toyota.com/content/dam/toyota/vehicles/rav4/rav4.png",
                year = 2024, category = "SUV", engineType = "Hybrid", transmission = "Automatic",
                horsepower = 219, priceAmount = 32000.0, priceCurrency = "USD",
                description = "The Toyota RAV4 is a compact crossover SUV produced by the Japanese automobile manufacturer Toyota."
            )
        ),
        "10" to listOf(  // Tesla
            CarModel(
                id = "tesla-1", name = "Model 3", brandId = "10", brandName = "Tesla",
                imageUrl = "https://www.tesla.com/content/dam/tesla/vehicles/model-3/model-3.png",
                year = 2024, category = "Electric", engineType = "Electric", transmission = "Automatic",
                horsepower = 283, priceAmount = 42000.0, priceCurrency = "USD",
                description = "The Tesla Model 3 is a battery electric compact executive sedan."
            ),
            CarModel(
                id = "tesla-2", name = "Model Y", brandId = "10", brandName = "Tesla",
                imageUrl = "https://www.tesla.com/content/dam/tesla/vehicles/model-y/model-y.png",
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
