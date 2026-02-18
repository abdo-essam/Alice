package com.ae.alice.presentation.screens.brands

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import alice.designsystem.generated.resources.Res
import alice.designsystem.generated.resources.no_brands_found
import alice.designsystem.generated.resources.search_brands_placeholder
import alice.designsystem.generated.resources.settings_title
import com.ae.alice.designsystem.components.ABottomNavBar
import org.jetbrains.compose.resources.stringResource
import com.ae.alice.designsystem.components.ADrawerContent
import com.ae.alice.designsystem.components.ADrawerItems
import com.ae.alice.designsystem.components.AGridCard
import com.ae.alice.designsystem.components.AHeader
import com.ae.alice.designsystem.components.ANavItems
import com.ae.alice.designsystem.components.ASearchField
import com.ae.alice.designsystem.components.AText
import com.ae.alice.designsystem.components.indicator.ADotsProgressIndicator
import com.ae.alice.designsystem.components.scaffold.AScaffold
import com.ae.alice.designsystem.theme.ATheme
import com.ae.alice.domain.entity.Brand
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

/**
 * Brands screen — car manufacturers grid with hamburger menu drawer.
 */
@Composable
fun BrandsScreen(
    onBrandClick: (Brand) -> Unit,
    onSettingsClick: () -> Unit,
    viewModel: BrandsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = ATheme.colorScheme.background.surface,
            ) {
                val drawerItems = ADrawerItems.default(selectedIndex = 0)
                ADrawerContent(
                    items = drawerItems,
                    onItemClick = { index ->
                        scope.launch { drawerState.close() }
                        if (drawerItems[index].label == Res.string.settings_title) {
                            onSettingsClick()
                        }
                    }
                )
            }
        }
    ) {
        AScaffold(
            backgroundColor = ATheme.colorScheme.background.surfaceLow,
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
                    items = ANavItems.default(selectedIndex = 0)
                )
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Search bar
                ASearchField(
                    value = state.searchQuery,
                    onValueChange = { viewModel.processIntent(BrandsIntent.Search(it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = ATheme.dimens.screenPaddingHorizontal,
                            vertical = ATheme.dimens.spacingSm
                        ),
                    placeholder = stringResource(Res.string.search_brands_placeholder),
                    onClear = { viewModel.processIntent(BrandsIntent.Search("")) }
                )

                Spacer(modifier = Modifier.height(ATheme.dimens.spacingSm))

                // Content
                when {
                    state.isLoading -> {
                        LoadingContent()
                    }
                    state.error != null -> {
                        ErrorContent(message = state.error ?: "An error occurred")
                    }
                    else -> {
                        BrandsGrid(
                            brands = state.filteredBrands,
                            onBrandClick = onBrandClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BrandsGrid(
    brands: List<Brand>,
    onBrandClick: (Brand) -> Unit
) {
    if (brands.isEmpty()) {
        EmptyContent()
        return
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(ATheme.dimens.screenPaddingHorizontal),
        horizontalArrangement = Arrangement.spacedBy(ATheme.dimens.spacingMd),
        verticalArrangement = Arrangement.spacedBy(ATheme.dimens.spacingMd)
    ) {
        items(
            items = brands,
            key = { it.id }
        ) { brand ->
            AGridCard(
                imageUrl = brand.logoUrl ?: "",
                title = brand.name,
                onClick = { onBrandClick(brand) }
            )
        }
    }
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ADotsProgressIndicator(
            colors = listOf(
                ATheme.colorScheme.brand.brand,
                ATheme.colorScheme.brand.brand.copy(alpha = 0.7f),
                ATheme.colorScheme.brand.brand.copy(alpha = 0.4f),
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
private fun ErrorContent(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AText(
            text = message,
            style = ATheme.typo.body.medium,
            color = ATheme.colorScheme.error
        )
    }
}

@Composable
private fun EmptyContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AText(
            text = stringResource(Res.string.no_brands_found),
            style = ATheme.typo.body.medium,
            color = ATheme.colorScheme.shadeSecondary
        )
    }
}
