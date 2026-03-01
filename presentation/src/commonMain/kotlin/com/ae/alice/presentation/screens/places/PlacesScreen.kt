package com.ae.alice.presentation.screens.places

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ae.alice.designsystem.components.ABottomNavBar
import com.ae.alice.designsystem.components.ADrawerContent
import com.ae.alice.designsystem.components.ADrawerItems
import com.ae.alice.designsystem.components.AHeader
import com.ae.alice.designsystem.components.ANavItems
import com.ae.alice.designsystem.components.APlaceCard
import com.ae.alice.designsystem.components.ASearchField
import com.ae.alice.designsystem.components.ASelector
import com.ae.alice.designsystem.components.ATabRow
import com.ae.alice.designsystem.theme.ATheme
import com.ae.alice.domain.entity.ServiceTab
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.places_search_placeholder
import alice.presentation.generated.resources.places_empty
import alice.presentation.generated.resources.places_error_default
import alice.presentation.generated.resources.places_location_label
import alice.presentation.generated.resources.places_tab_one
import alice.presentation.generated.resources.places_tab_two
import alice.presentation.generated.resources.places_category_label
import org.jetbrains.compose.resources.stringResource
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

/**
 * Places screen displaying service providers organized by categories and tabs.
 */
@Composable
fun PlacesScreen(
    viewModel: PlacesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val locations = listOf("الرياض", "جدة", "الدمام", "الخبر", "مكة المكرمة", "المدينة المنورة")

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                ADrawerContent(
                    items = ADrawerItems.default(selectedIndex = -1),
                    onItemClick = {
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        Scaffold(
            containerColor = ATheme.colors.Light.Background,
            topBar = {
                AHeader(
                    showMenuIcon = true,
                    onMenuClick = {
                        scope.launch { drawerState.open() }
                    }
                )
            },
            bottomBar = {
                ABottomNavBar(
                    items = ANavItems.default(selectedIndex = 1)
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // ── Location selector ──
                ASelector(
                    label = stringResource(Res.string.places_location_label),
                    selectedValue = state.selectedLocation,
                    options = locations,
                    onOptionSelected = {
                        viewModel.processIntent(PlacesIntent.SelectLocation(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = ATheme.dimens.ScreenPaddingHorizontal,
                            vertical = ATheme.dimens.SpacingSm
                        )
                )

                // ── Search bar ──
                ASearchField(
                    value = state.searchQuery,
                    onValueChange = { viewModel.processIntent(PlacesIntent.Search(it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = ATheme.dimens.ScreenPaddingHorizontal),
                    placeholder = stringResource(Res.string.places_search_placeholder),
                    onClear = { viewModel.processIntent(PlacesIntent.Search("")) }
                )

                Spacer(modifier = Modifier.height(ATheme.dimens.SpacingMd))

                // ── Tabs ──
                ATabRow(
                    tabs = listOf(
                        stringResource(Res.string.places_tab_one),
                        stringResource(Res.string.places_tab_two)
                    ),
                    selectedIndex = if (state.selectedTab == ServiceTab.TAB_ONE) 0 else 1,
                    onTabSelected = { index ->
                        val tab = if (index == 0) ServiceTab.TAB_ONE else ServiceTab.TAB_TWO
                        viewModel.processIntent(PlacesIntent.SelectTab(tab))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = ATheme.dimens.ScreenPaddingHorizontal)
                )

                Spacer(modifier = Modifier.height(ATheme.dimens.SpacingMd))

                // ── Category dropdown ──
                if (state.filteredCategories.isNotEmpty()) {
                    ASelector(
                        label = stringResource(Res.string.places_category_label),
                        selectedValue = state.selectedCategory?.name ?: "",
                        options = state.filteredCategories.map { it.name },
                        onOptionSelected = { name ->
                            val category = state.filteredCategories.firstOrNull { it.name == name }
                            category?.let {
                                viewModel.processIntent(PlacesIntent.SelectCategory(it))
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = ATheme.dimens.ScreenPaddingHorizontal)
                    )

                    Spacer(modifier = Modifier.height(ATheme.dimens.SpacingMd))
                }

                // ── Content ──
                when {
                    state.isLoading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = ATheme.colors.Primary)
                        }
                    }
                    state.error != null -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = state.error ?: stringResource(Res.string.places_error_default),
                                color = ATheme.colors.Error
                            )
                        }
                    }
                    state.places.isEmpty() -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(Res.string.places_empty),
                                color = ATheme.colors.Light.TextSecondary
                            )
                        }
                    }
                    else -> {
                        LazyColumn(
                            contentPadding = PaddingValues(
                                horizontal = ATheme.dimens.ScreenPaddingHorizontal,
                                vertical = ATheme.dimens.SpacingSm
                            ),
                            verticalArrangement = Arrangement.spacedBy(ATheme.dimens.SpacingMd)
                        ) {
                            items(
                                items = state.places,
                                key = { it.id }
                            ) { place ->
                                APlaceCard(
                                    name = place.name,
                                    address = place.address,
                                    imageUrl = place.imageUrl,
                                    isSaved = place.isSaved,
                                    onDetailsClick = {
                                        viewModel.processIntent(
                                            PlacesIntent.PlaceDetailsClicked(place)
                                        )
                                    },
                                    onSaveClick = {
                                        viewModel.processIntent(
                                            PlacesIntent.ToggleSave(place.id)
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
