package com.ae.alice.screens.brands

import com.ae.alice.domain.entity.Brand
import com.ae.alice.domain.repository.BrandRepository
import com.ae.alice.presentation.base.BaseViewModel
import com.ae.alice.presentation.base.UiEffect
import com.ae.alice.presentation.base.UiIntent
import com.ae.alice.presentation.base.UiState

/**
 * ViewModel for BrandsScreen using MVI pattern.
 */
class BrandsViewModel(
    private val brandRepository: BrandRepository
) : BaseViewModel<BrandsState, BrandsIntent, BrandsEffect>(BrandsState()) {
    
    init {
        loadBrands()
    }
    
    override fun handleIntent(intent: BrandsIntent) {
        when (intent) {
            is BrandsIntent.LoadBrands -> loadBrands()
            is BrandsIntent.Search -> onSearch(intent.query)
            is BrandsIntent.ClearSearch -> clearSearch()
            is BrandsIntent.BrandClicked -> { /* Handled by UI navigation */ }
        }
    }
    
    private fun loadBrands() {
        launch {
            updateState { copy(isLoading = true, error = null) }
            try {
                val brands = brandRepository.getBrands()
                updateState { 
                    copy(
                        isLoading = false,
                        brands = brands,
                        filteredBrands = brands
                    ) 
                }
            } catch (e: Exception) {
                updateState { 
                    copy(
                        isLoading = false,
                        error = e.message ?: "Failed to load brands"
                    ) 
                }
            }
        }
    }
    
    private fun onSearch(query: String) {
        updateState { copy(searchQuery = query) }
        filterBrands(query)
    }
    
    private fun filterBrands(query: String) {
        val filtered = if (query.isBlank()) {
            currentState.brands
        } else {
            currentState.brands.filter {
                it.name.contains(query, ignoreCase = true) ||
                it.country?.contains(query, ignoreCase = true) == true
            }
        }
        updateState { copy(filteredBrands = filtered) }
    }
    
    private fun clearSearch() {
        updateState { 
            copy(
                searchQuery = "",
                filteredBrands = brands
            ) 
        }
    }
}

/**
 * UI State for BrandsScreen.
 */
data class BrandsState(
    val isLoading: Boolean = false,
    val brands: List<Brand> = emptyList(),
    val filteredBrands: List<Brand> = emptyList(),
    val searchQuery: String = "",
    val error: String? = null
) : UiState

/**
 * Intents for BrandsScreen.
 */
sealed interface BrandsIntent : UiIntent {
    data object LoadBrands : BrandsIntent
    data class Search(val query: String) : BrandsIntent
    data object ClearSearch : BrandsIntent
    data class BrandClicked(val brand: Brand) : BrandsIntent
}

/**
 * Effects for BrandsScreen.
 */
sealed interface BrandsEffect : UiEffect {
    data class NavigateToModels(val brand: Brand) : BrandsEffect
}
