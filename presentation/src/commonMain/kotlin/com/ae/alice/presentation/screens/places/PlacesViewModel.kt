package com.ae.alice.presentation.screens.places

import androidx.lifecycle.viewModelScope
import com.ae.alice.domain.entity.ServiceCategory
import com.ae.alice.domain.entity.ServiceTab
import com.ae.alice.domain.repository.PlaceRepository
import com.ae.alice.presentation.base.BaseViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(FlowPreview::class)
class PlacesViewModel(
    private val placeRepository: PlaceRepository
) : BaseViewModel<PlacesState, PlacesIntent, PlacesEffect>(PlacesState()) {

    private val searchFlow = MutableSharedFlow<String>(extraBufferCapacity = 1)
    private var searchJob: Job? = null

    init {
        observeSearch()
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

    private fun observeSearch() {
        searchFlow
            .debounce(SEARCH_DEBOUNCE_MS)
            .distinctUntilChanged()
            .onEach { query -> executeSearch(query) }
            .launchIn(viewModelScope) // Use viewModelScope from androidx.lifecycle
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
                    copy(isLoading = false, error = e.message ?: "Failed to load data")
                }
            }
        )
    }

    private fun onTabSelected(tab: ServiceTab) {
        if (tab == currentState.selectedTab) return

        val filtered = currentState.categories.filter { it.tab == tab }
        val firstCategory = filtered.firstOrNull()
        updateState {
            copy(
                selectedTab = tab,
                filteredCategories = filtered,
                selectedCategory = firstCategory,
                searchQuery = "",
                places = emptyList()
            )
        }
        firstCategory?.let { loadPlacesForCategory(it.id) }
    }

    private fun onCategorySelected(category: ServiceCategory) {
        if (category.id == currentState.selectedCategory?.id) return
        updateState { copy(selectedCategory = category, searchQuery = "") }
        loadPlacesForCategory(category.id)
    }

    private fun loadPlacesForCategory(categoryId: String) {
        updateState { copy(isPlacesLoading = true) }
        tryExecute(
            call = {
                placeRepository.getPlacesByCategory(categoryId, currentState.selectedLocation)
            },
            onSuccess = { places ->
                updateState { copy(places = places, isPlacesLoading = false) }
            },
            onError = { e ->
                updateState {
                    copy(isPlacesLoading = false, error = e.message ?: "Failed to load places")
                }
            }
        )
    }

    private fun onSearch(query: String) {
        updateState { copy(searchQuery = query) }
        if (query.isBlank()) {
            searchJob?.cancel()
            currentState.selectedCategory?.let { loadPlacesForCategory(it.id) }
            return
        }
        searchFlow.tryEmit(query)
    }

    private fun executeSearch(query: String) {
        if (query.isBlank()) return
        updateState { copy(isPlacesLoading = true) }
        tryExecute(
            call = {
                placeRepository.searchPlaces(query, currentState.selectedLocation)
            },
            onSuccess = { places ->
                updateState { copy(places = places, isPlacesLoading = false) }
            },
            onError = { e ->
                updateState { copy(isPlacesLoading = false) }
                emitEffect(PlacesEffect.ShowError(e.message ?: "Search failed"))
            }
        )
    }

    private fun onLocationSelected(location: String) {
        if (location == currentState.selectedLocation) return
        updateState { copy(selectedLocation = location) }
        if (currentState.searchQuery.isNotBlank()) {
            executeSearch(currentState.searchQuery)
        } else {
            currentState.selectedCategory?.let { loadPlacesForCategory(it.id) }
        }
    }

    private fun onToggleSave(placeId: String) {
        val place = currentState.places.find { it.id == placeId } ?: return
        val newSavedState = !place.isSaved

        // Optimistic update
        updateState {
            copy(places = places.map {
                if (it.id == placeId) it.copy(isSaved = newSavedState) else it
            })
        }

        // Persist to repository
        tryExecute(
            call = {
                if (newSavedState) {
                    placeRepository.savePlace(placeId)
                } else {
                    placeRepository.unsavePlace(placeId)
                }
            },
            onSuccess = { /* already updated optimistically */ },
            onError = { _ ->
                // Rollback
                updateState {
                    copy(places = places.map {
                        if (it.id == placeId) it.copy(isSaved = !newSavedState) else it
                    })
                }
                emitEffect(PlacesEffect.ShowError("Failed to update saved status"))
            }
        )
    }

    companion object {
        private const val SEARCH_DEBOUNCE_MS = 400L
    }
}
