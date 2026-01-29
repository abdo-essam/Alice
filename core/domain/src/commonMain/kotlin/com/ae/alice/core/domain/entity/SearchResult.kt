package com.ae.alice.core.domain.entity

/**
 * Domain entity for search results combining brands and models
 */
data class SearchResult(
    val brands: List<Brand>,
    val models: List<CarModel>,
    val query: String,
    val totalBrandsCount: Int = brands.size,
    val totalModelsCount: Int = models.size
) {
    val isEmpty: Boolean
        get() = brands.isEmpty() && models.isEmpty()
    
    val totalCount: Int
        get() = totalBrandsCount + totalModelsCount
}

/**
 * Search filters for filtering car models
 */
data class SearchFilters(
    val query: String = "",
    val brandIds: List<String> = emptyList(),
    val categories: List<CarCategory> = emptyList(),
    val engineTypes: List<EngineType> = emptyList(),
    val transmissionTypes: List<TransmissionType> = emptyList(),
    val minYear: Int? = null,
    val maxYear: Int? = null,
    val minPrice: Double? = null,
    val maxPrice: Double? = null,
    val onlyFavorites: Boolean = false
) {
    val isActive: Boolean
        get() = query.isNotBlank() ||
                brandIds.isNotEmpty() ||
                categories.isNotEmpty() ||
                engineTypes.isNotEmpty() ||
                transmissionTypes.isNotEmpty() ||
                minYear != null ||
                maxYear != null ||
                minPrice != null ||
                maxPrice != null ||
                onlyFavorites
    
    fun clear(): SearchFilters = SearchFilters()
}

/**
 * Sort options for lists
 */
enum class SortOption {
    NAME_ASC,
    NAME_DESC,
    YEAR_ASC,
    YEAR_DESC,
    PRICE_ASC,
    PRICE_DESC,
    POPULARITY
}
