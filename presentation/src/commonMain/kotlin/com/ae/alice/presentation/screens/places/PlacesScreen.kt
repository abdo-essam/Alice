package com.ae.alice.presentation.screens.places

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.places_category_label
import alice.presentation.generated.resources.places_empty
import alice.presentation.generated.resources.places_location_label
import alice.presentation.generated.resources.places_search_placeholder
import alice.presentation.generated.resources.places_tab_one
import alice.presentation.generated.resources.places_tab_two
import com.ae.alice.designsystem.components.appBar.HomeAppBar
import com.ae.alice.designsystem.components.card.PlaceCard
import com.ae.alice.designsystem.components.scaffold.Scaffold
import com.ae.alice.designsystem.components.selector.Selector
import com.ae.alice.designsystem.components.segment.Segment
import com.ae.alice.designsystem.components.state.EmptyLayout
import com.ae.alice.designsystem.components.state.ErrorLayout
import com.ae.alice.designsystem.components.state.LoadingLayout
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.components.textField.SearchField
import com.ae.alice.designsystem.theme.Theme
import com.ae.alice.domain.entity.ServiceTab
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

/**
 * Places screen — inner screen, no bottom navigation bar.
 */
@Composable
fun PlacesScreen(
    onPickLocationClick: () -> Unit,
    passedLocation: String? = null,
    viewModel: PlacesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(passedLocation) {
        if (!passedLocation.isNullOrEmpty()) {
            viewModel.processIntent(PlacesIntent.SelectLocation(passedLocation))
        }
    }
    val locations = listOf("الرياض", "جدة", "الدمام", "الخبر", "مكة المكرمة", "المدينة المنورة")

    Scaffold(
        backgroundColor = Theme.colorScheme.background.surfaceLow,
        topBar = { HomeAppBar() },
    ) {
        when {
            state.isLoading -> LoadingLayout()
            state.error != null -> {
                ErrorLayout(
                    title = state.error ?: "Error",
                    onRetry = { viewModel.processIntent(PlacesIntent.LoadData) }
                )
            }
            else -> {
                PlacesContent(
                    state = state,
                    viewModel = viewModel,
                    onPickLocationClick = onPickLocationClick
                )
            }
        }
    }
}

@Composable
private fun PlacesContent(
    state: PlacesState,
    viewModel: PlacesViewModel,
    onPickLocationClick: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = Theme.spacing._16)
    ) {
        // Location selector
        item {
            com.ae.alice.designsystem.components.dropdown.DropdownSelector(
                label = stringResource(Res.string.places_location_label),
                selectedValue = state.selectedLocation,
                options = emptyList(), // Not used anymore since we have a dedicated screen
                onOptionSelected = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = Theme.spacing._16,
                        vertical = Theme.spacing._8
                    )
                    .clickable { onPickLocationClick() },
                placeholder = "اختيار الموقع"
            )
        }

        // Search bar
        item {
            SearchField(
                value = state.searchQuery,
                onValueChange = { viewModel.processIntent(PlacesIntent.Search(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Theme.spacing._16),
                placeholder = stringResource(Res.string.places_search_placeholder),
                onClear = { viewModel.processIntent(PlacesIntent.Search("")) }
            )
            Spacer(modifier = Modifier.height(Theme.spacing._12))
        }

        // Tabs
        item {
            Segment(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Theme.spacing._16)
            ) {
                item(stringResource(Res.string.places_tab_one)) {
                    LaunchedEffect(state.selectedTab) {
                        if (state.selectedTab != ServiceTab.TAB_ONE) {
                            viewModel.processIntent(PlacesIntent.SelectTab(ServiceTab.TAB_ONE))
                        }
                    }
                }
                item(stringResource(Res.string.places_tab_two)) {
                    LaunchedEffect(state.selectedTab) {
                        if (state.selectedTab != ServiceTab.TAB_TWO) {
                            viewModel.processIntent(PlacesIntent.SelectTab(ServiceTab.TAB_TWO))
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(Theme.spacing._12))
        }

        // Category selector
        if (state.filteredCategories.isNotEmpty()) {
            item {
                Selector(
                    label = stringResource(Res.string.places_category_label),
                    selectedValue = state.selectedCategory?.name ?: "",
                    options = state.filteredCategories.map { it.name },
                    onOptionSelected = { name ->
                        val category =
                            state.filteredCategories.firstOrNull { it.name == name }
                        category?.let {
                            viewModel.processIntent(PlacesIntent.SelectCategory(it))
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Theme.spacing._16)
                )
                Spacer(modifier = Modifier.height(Theme.spacing._12))
            }
        }

        // Places list
        if (state.places.isEmpty() && !state.isLoading) {
            item {
                EmptyLayout(
                    title = stringResource(Res.string.places_empty),
                )
            }
        } else {
            items(state.places, key = { it.id }) { place ->
                PlaceCard(
                    name = place.name,
                    address = place.address,
                    imageUrl = place.imageUrl,
                    isSaved = place.isSaved,
                    onDetailsClick = {
                        viewModel.processIntent(PlacesIntent.PlaceDetailsClicked(place))
                    },
                    onSaveClick = {
                        viewModel.processIntent(PlacesIntent.ToggleSave(place.id))
                    },
                    modifier = Modifier.padding(
                        horizontal = Theme.spacing._16,
                        vertical = Theme.spacing._4
                    )
                )
            }
        }
    }
}
