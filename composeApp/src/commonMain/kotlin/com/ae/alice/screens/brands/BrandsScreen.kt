package com.ae.alice.screens.brands

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.ABottomNavBar
import com.ae.alice.designsystem.components.AGridCard
import com.ae.alice.designsystem.components.AHeader
import com.ae.alice.designsystem.components.ANavItems
import com.ae.alice.designsystem.components.ASearchField
import com.ae.alice.designsystem.theme.AColors
import com.ae.alice.domain.entity.Brand
import org.koin.compose.viewmodel.koinViewModel

/**
 * Brands screen displaying car manufacturers in a grid layout.
 */
@Composable
fun BrandsScreen(
    onBrandClick: (Brand) -> Unit,
    viewModel: BrandsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    
    Scaffold(
        containerColor = AColors.Light.Background,
        topBar = {
            Column {
                AHeader(
                    cartCount = 0,
                    notificationCount = 0
                )
            }
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
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = "Search brands...",
                onClear = { viewModel.processIntent(BrandsIntent.Search("")) }
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
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
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
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
        CircularProgressIndicator(color = AColors.Primary)
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
            color = AColors.Error
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
            text = "No brands found",
            color = AColors.Light.TextSecondary
        )
    }
}
