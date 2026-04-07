package com.ae.alice.presentation.screens.places

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.places_category_label
import alice.presentation.generated.resources.places_empty
import alice.presentation.generated.resources.places_location_label
import alice.presentation.generated.resources.places_search_placeholder
import alice.presentation.generated.resources.places_tab_one
import alice.presentation.generated.resources.places_tab_two
import alice.presentation.generated.resources.ic_arrow_left
import com.ae.alice.designsystem.components.appBar.AppBar
import com.ae.alice.designsystem.components.bottomSheet.BottomSheet
import com.ae.alice.designsystem.components.bottomSheet.SelectionSheetContent
import com.ae.alice.designsystem.components.card.PlaceCard
import com.ae.alice.designsystem.components.scaffold.Scaffold
import com.ae.alice.designsystem.components.segment.SegmentTabRow
import com.ae.alice.designsystem.components.selector.Selector
import com.ae.alice.designsystem.components.state.ErrorLayout
import com.ae.alice.designsystem.components.state.LoadingLayout
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.components.textfield.SearchField
import com.ae.alice.designsystem.theme.Theme
import com.ae.alice.domain.entity.Place
import com.ae.alice.domain.entity.ServiceTab
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PlacesScreen(
    onNavigateToDetails: (Place) -> Unit,
    onBackClick: () -> Unit,
    passedLocation: String? = null,
    viewModel: PlacesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(passedLocation) {
        if (!passedLocation.isNullOrEmpty()) {
            viewModel.processIntent(PlacesIntent.SelectLocation(passedLocation))
        }
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is PlacesEffect.NavigateToPlaceDetails -> onNavigateToDetails(effect.place)
                is PlacesEffect.ShowError -> { /* Snackbar */ }
            }
        }
    }

    Scaffold(
        backgroundColor = Theme.colorScheme.background.surfaceLow,
        topBar = {
            AppBar(
                title = stringResource(Res.string.places_location_label),
                leadingContent = {
                    com.ae.alice.designsystem.components.icon.Icon(
                        painter = org.jetbrains.compose.resources.painterResource(Res.drawable.ic_arrow_left),
                        contentDescription = "Back",
                        tint = Theme.colorScheme.primary.primary
                    )
                },
                onLeadingClick = onBackClick
            )
        },
        overlays = {
            // ── Location Bottom Sheet ──
            bottomSheet(isVisible = state.showLocationSheet) { isVisible ->
                BottomSheet(
                    isVisible = isVisible,
                    onDismissRequest = { viewModel.processIntent(PlacesIntent.DismissLocationSheet) },
                    sheetContent = {
                        SelectionSheetContent(
                            title = stringResource(Res.string.places_location_label),
                            options = state.locations.map { it.name },
                            selectedOption = state.selectedLocation,
                            onOptionSelected = { location ->
                                viewModel.processIntent(PlacesIntent.SelectLocation(location))
                            },
                            searchPlaceholder = stringResource(Res.string.places_search_placeholder),
                        )
                    }
                )
            }

            // ── Category Bottom Sheet ──
            bottomSheet(isVisible = state.showCategorySheet) { isVisible ->
                BottomSheet(
                    isVisible = isVisible,
                    onDismissRequest = { viewModel.processIntent(PlacesIntent.DismissCategorySheet) },
                    sheetContent = {
                        SelectionSheetContent(
                            title = stringResource(Res.string.places_category_label),
                            options = state.filteredCategories.map { it.name },
                            selectedOption = state.selectedCategory?.name ?: "",
                            onOptionSelected = { name ->
                                state.filteredCategories
                                    .firstOrNull { it.name == name }
                                    ?.let { viewModel.processIntent(PlacesIntent.SelectCategory(it)) }
                            },
                            searchPlaceholder = stringResource(Res.string.places_search_placeholder),
                        )
                    }
                )
            }
        }
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
                    onSearchChanged = { viewModel.processIntent(PlacesIntent.Search(it)) },
                    onClearSearch = { viewModel.processIntent(PlacesIntent.Search("")) },
                    onTabSelected = { viewModel.processIntent(PlacesIntent.SelectTab(it)) },
                    onLocationSelectorClick = { viewModel.processIntent(PlacesIntent.ShowLocationSheet) },
                    onCategorySelectorClick = { viewModel.processIntent(PlacesIntent.ShowCategorySheet) },
                    onPlaceDetailsClick = { viewModel.processIntent(PlacesIntent.PlaceDetailsClicked(it)) },
                    onToggleSave = { viewModel.processIntent(PlacesIntent.ToggleSave(it)) },
                )
            }
        }
    }
}

@Composable
private fun PlacesContent(
    state: PlacesState,
    onSearchChanged: (String) -> Unit,
    onClearSearch: () -> Unit,
    onTabSelected: (ServiceTab) -> Unit,
    onLocationSelectorClick: () -> Unit,
    onCategorySelectorClick: () -> Unit,
    onPlaceDetailsClick: (Place) -> Unit,
    onToggleSave: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = Theme.spacing._16,
            bottom = Theme.spacing._24,
        )
    ) {
        // ── Location selector (no label) ──
        item(key = "location") {
            Selector(
                selectedValue = state.selectedLocation,
                onClick = onLocationSelectorClick,
                isExpanded = state.showLocationSheet,
                placeholder = stringResource(Res.string.places_location_label),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Theme.spacing._16),
            )
            Spacer(modifier = Modifier.height(Theme.spacing._16))
        }

        // ── Search bar ──
        item(key = "search") {
            SearchField(
                value = state.searchQuery,
                onValueChange = onSearchChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Theme.spacing._16),
                placeholder = stringResource(Res.string.places_search_placeholder),
                onClear = onClearSearch
            )
            Spacer(modifier = Modifier.height(Theme.spacing._16))
        }

        // ── Tabs ──
        item(key = "tabs") {
            val tabs = listOf(
                ServiceTab.TAB_ONE to stringResource(Res.string.places_tab_one),
                ServiceTab.TAB_TWO to stringResource(Res.string.places_tab_two),
            )
            val selectedIndex = tabs.indexOfFirst { it.first == state.selectedTab }
                .coerceAtLeast(0)

            SegmentTabRow(
                tabs = tabs.map { it.second },
                selectedIndex = selectedIndex,
                onTabSelected = { index -> onTabSelected(tabs[index].first) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Theme.spacing._16),
            )
            Spacer(modifier = Modifier.height(Theme.spacing._16))
        }

        // ── Category selector (no label) ──
        if (state.filteredCategories.isNotEmpty()) {
            item(key = "category") {
                Selector(
                    selectedValue = state.selectedCategory?.name ?: "",
                    onClick = onCategorySelectorClick,
                    isExpanded = state.showCategorySheet,
                    placeholder = stringResource(Res.string.places_category_label),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Theme.spacing._16),
                )
                Spacer(modifier = Modifier.height(Theme.spacing._16))
            }
        }

        // ── Places list ──
        when {
            state.isPlacesLoading -> {
                item(key = "places_loading") {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(Theme.spacing._40 * 4),
                        contentAlignment = Alignment.Center,
                    ) {
                        LoadingLayout(
                            backgroundColor = Theme.colorScheme.background.surfaceLow,
                        )
                    }
                }
            }

            state.places.isEmpty() -> {
                item(key = "places_empty") {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = Theme.spacing._40),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = stringResource(Res.string.places_empty),
                            style = Theme.typography.body.medium,
                            color = Theme.colorScheme.shadeTertiary,
                        )
                    }
                }
            }

            else -> {
                items(
                    items = state.places,
                    key = { it.id }
                ) { place ->
                    PlaceCard(
                        name = place.name,
                        address = place.address,
                        imageUrl = place.imageUrl,
                        isSaved = place.isSaved,
                        onDetailsClick = { onPlaceDetailsClick(place) },
                        onSaveClick = { onToggleSave(place.id) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = Theme.spacing._16,
                                vertical = Theme.spacing._4
                            )
                            .animateItem()
                    )
                }

                item(key = "bottom_spacer") {
                    Spacer(modifier = Modifier.height(Theme.spacing._16))
                }
            }
        }
    }
}
