package com.ae.alice.presentation.screens.places

import com.ae.alice.domain.entity.Place
import com.ae.alice.domain.entity.ServiceCategory
import com.ae.alice.domain.entity.ServiceTab
import com.ae.alice.presentation.base.UiEffect
import com.ae.alice.presentation.base.UiIntent
import com.ae.alice.presentation.base.UiState

data class PlacesState(
    val isLoading: Boolean = false,
    val isPlacesLoading: Boolean = false,
    val searchQuery: String = "",
    val selectedTab: ServiceTab = ServiceTab.TAB_ONE,
    val categories: List<ServiceCategory> = emptyList(),
    val filteredCategories: List<ServiceCategory> = emptyList(),
    val selectedCategory: ServiceCategory? = null,
    val places: List<Place> = emptyList(),
    val selectedLocation: String = "الرياض",
    val error: String? = null
) : UiState

sealed interface PlacesIntent : UiIntent {
    data object LoadData : PlacesIntent
    data class SelectTab(val tab: ServiceTab) : PlacesIntent
    data class SelectCategory(val category: ServiceCategory) : PlacesIntent
    data class Search(val query: String) : PlacesIntent
    data class SelectLocation(val location: String) : PlacesIntent
    data class ToggleSave(val placeId: String) : PlacesIntent
    data class PlaceDetailsClicked(val place: Place) : PlacesIntent
}

sealed interface PlacesEffect : UiEffect {
    data class NavigateToPlaceDetails(val place: Place) : PlacesEffect
    data class ShowError(val message: String) : PlacesEffect
}
