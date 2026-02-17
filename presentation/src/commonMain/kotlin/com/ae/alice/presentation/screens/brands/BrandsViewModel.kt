package com.ae.alice.presentation.screens.brands

import com.ae.alice.domain.repository.BrandRepository
import com.ae.alice.presentation.base.BaseViewModel

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
        updateState { copy(isLoading = true, error = null) }
        tryExecute(
            call = { brandRepository.getBrands() },
            onSuccess = { brands ->
                updateState { 
                    copy(
                        isLoading = false,
                        brands = brands,
                        filteredBrands = brands
                    ) 
                }
            },
            onError = { e ->
                updateState { 
                    copy(
                        isLoading = false,
                        error = e.message ?: "Failed to load brands"
                    ) 
                }
            }
        )
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
