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
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.ABottomNavBar
import com.ae.alice.designsystem.components.ADrawerContent
import com.ae.alice.designsystem.components.ADrawerItems
import com.ae.alice.designsystem.components.AGridCard
import com.ae.alice.designsystem.components.AHeader
import com.ae.alice.designsystem.components.ANavItems
import com.ae.alice.designsystem.components.ASearchField
import com.ae.alice.designsystem.theme.ATheme
import com.ae.alice.domain.entity.Brand
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.brands_search_placeholder
import alice.presentation.generated.resources.brands_empty
import alice.presentation.generated.resources.brands_error_default
import org.jetbrains.compose.resources.stringResource
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

/**
 * Brands screen displaying car manufacturers in a grid layout
 * with a hamburger menu drawer.
 */
@Composable
fun BrandsScreen(
    onBrandClick: (Brand) -> Unit,
    viewModel: BrandsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                ADrawerContent(
                    items = ADrawerItems.default(selectedIndex = 0),
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
                    items = ANavItems.default(selectedIndex = 0)
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // Search bar
                ASearchField(
                    value = state.searchQuery,
                    onValueChange = { viewModel.processIntent(BrandsIntent.Search(it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = ATheme.dimens.ScreenPaddingHorizontal,
                            vertical = ATheme.dimens.SpacingSm
                        ),
                    placeholder = stringResource(Res.string.brands_search_placeholder),
                    onClear = { viewModel.processIntent(BrandsIntent.Search("")) }
                )

                Spacer(modifier = Modifier.height(ATheme.dimens.SpacingSm))

                // Content
                when {
                    state.isLoading -> {
                        LoadingContent()
                    }
                    state.error != null -> {
                        ErrorContent(message = state.error ?: stringResource(Res.string.brands_error_default))
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
        contentPadding = PaddingValues(ATheme.dimens.ScreenPaddingHorizontal),
        horizontalArrangement = Arrangement.spacedBy(ATheme.dimens.SpacingMd),
        verticalArrangement = Arrangement.spacedBy(ATheme.dimens.SpacingMd)
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
        CircularProgressIndicator(color = ATheme.colors.Primary)
    }
}

@Composable
private fun ErrorContent(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = ATheme.colors.Error
        )
    }
}

@Composable
private fun EmptyContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(Res.string.brands_empty),
            color = ATheme.colors.Light.TextSecondary
        )
    }
}
