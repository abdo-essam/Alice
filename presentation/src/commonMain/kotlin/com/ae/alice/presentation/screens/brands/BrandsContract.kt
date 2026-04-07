package com.ae.alice.presentation.screens.brands

import com.ae.alice.domain.entity.Brand
import com.ae.alice.presentation.base.UiEffect
import com.ae.alice.presentation.base.UiIntent
import com.ae.alice.presentation.base.UiState

data class BrandsState(
    val isLoading: Boolean = false,
    val brands: List<Brand> = emptyList(),
    val filteredBrands: List<Brand> = emptyList(),
    val searchQuery: String = "",
    val error: String? = null
) : UiState

sealed interface BrandsIntent : UiIntent {
    data object LoadBrands : BrandsIntent
    data class Search(val query: String) : BrandsIntent
    data object ClearSearch : BrandsIntent
    data class BrandClicked(val brand: Brand) : BrandsIntent
}
sealed interface BrandsEffect : UiEffect {
    data class NavigateToModels(val brand: Brand) : BrandsEffect
}
