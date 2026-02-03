package com.ae.alice.screens.brands

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.ae.alice.core.domain.entity.Brand
import com.ae.alice.core.domain.repository.BrandRepository

/**
 * ViewModel for BrandsScreen.
 */
class BrandsViewModel(
    private val brandRepository: BrandRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(BrandsUiState())
    val uiState: StateFlow<BrandsUiState> = _uiState.asStateFlow()
    
    init {
        loadBrands()
    }
    
    fun loadBrands() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                val brands = brandRepository.getBrands()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    brands = brands,
                    filteredBrands = brands
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load brands"
                )
            }
        }
    }
    
    fun onSearchQueryChange(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
        filterBrands(query)
    }
    
    private fun filterBrands(query: String) {
        val filtered = if (query.isBlank()) {
            _uiState.value.brands
        } else {
            _uiState.value.brands.filter {
                it.name.contains(query, ignoreCase = true) ||
                it.country?.contains(query, ignoreCase = true) == true
            }
        }
        _uiState.value = _uiState.value.copy(filteredBrands = filtered)
    }
    
    fun clearSearch() {
        _uiState.value = _uiState.value.copy(
            searchQuery = "",
            filteredBrands = _uiState.value.brands
        )
    }
}

/**
 * UI State for BrandsScreen.
 */
data class BrandsUiState(
    val isLoading: Boolean = false,
    val brands: List<Brand> = emptyList(),
    val filteredBrands: List<Brand> = emptyList(),
    val searchQuery: String = "",
    val error: String? = null
)
