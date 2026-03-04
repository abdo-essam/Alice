package com.ae.alice.presentation.screens.places

import com.ae.alice.domain.entity.ServiceTab
import com.ae.alice.domain.repository.PlaceRepository
import com.ae.alice.presentation.base.BaseViewModel

/**
 * ViewModel for PlacesScreen using MVI pattern.
 */
class PlacesViewModel(
    private val placeRepository: PlaceRepository
) : BaseViewModel<PlacesState, PlacesIntent, PlacesEffect>(PlacesState()) {

    init {
        loadData()
    }

    override fun handleIntent(intent: PlacesIntent) {
        when (intent) {
            is PlacesIntent.LoadData -> loadData()
            is PlacesIntent.SelectTab -> onTabSelected(intent.tab)
            is PlacesIntent.SelectCategory -> onCategorySelected(intent.category)
            is PlacesIntent.Search -> onSearch(intent.query)
            is PlacesIntent.SelectLocation -> onLocationSelected(intent.location)
            is PlacesIntent.ToggleSave -> onToggleSave(intent.placeId)
            is PlacesIntent.PlaceDetailsClicked -> {
                emitEffect(PlacesEffect.NavigateToPlaceDetails(intent.place))
            }
        }
    }

    private fun loadData() {
        updateState { copy(isLoading = true, error = null) }
        tryExecute(
            call = { placeRepository.getCategories() },
            onSuccess = { categories ->
                val filtered = categories.filter { it.tab == currentState.selectedTab }
                val firstCategory = filtered.firstOrNull()
                updateState {
                    copy(
                        isLoading = false,
                        categories = categories,
                        filteredCategories = filtered,
                        selectedCategory = firstCategory
                    )
                }
                firstCategory?.let { loadPlacesForCategory(it.id) }
            },
            onError = { e ->
                updateState {
                    copy(
                        isLoading = false,
                        error = e.message ?: "Failed to load data"
                    )
                }
            }
        )
    }

    private fun onTabSelected(tab: ServiceTab) {
        val filtered = currentState.categories.filter { it.tab == tab }
        val firstCategory = filtered.firstOrNull()
        updateState {
            copy(
                selectedTab = tab,
                filteredCategories = filtered,
                selectedCategory = firstCategory,
                searchQuery = ""
            )
        }
        firstCategory?.let { loadPlacesForCategory(it.id) }
    }

    private fun onCategorySelected(category: com.ae.alice.domain.entity.ServiceCategory) {
        updateState { copy(selectedCategory = category, searchQuery = "") }
        loadPlacesForCategory(category.id)
    }

    private fun loadPlacesForCategory(categoryId: String) {
        tryExecute(
            call = { placeRepository.getPlacesByCategory(categoryId) },
            onSuccess = { places ->
                updateState { copy(places = places) }
            },
            onError = { e ->
                updateState { copy(error = e.message ?: "Failed to load places") }
            }
        )
    }

    private fun onSearch(query: String) {
        updateState { copy(searchQuery = query) }
        if (query.isBlank()) {
            currentState.selectedCategory?.let { loadPlacesForCategory(it.id) }
            return
        }
        tryExecute(
            call = { placeRepository.searchPlaces(query) },
            onSuccess = { places ->
                updateState { copy(places = places) }
            },
            onError = { e ->
                updateState { copy(error = e.message ?: "Search failed") }
            }
        )
    }

    private fun onLocationSelected(location: String) {
        updateState { copy(selectedLocation = location) }
    }

    private fun onToggleSave(placeId: String) {
        updateState {
            copy(
                places = places.map {
                    if (it.id == placeId) it.copy(isSaved = !it.isSaved) else it
                }
            )
        }
    }
}
