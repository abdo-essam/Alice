package com.ae.alice.screens.brands

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel
import com.ae.alice.core.designsystem.components.ACard
import com.ae.alice.core.designsystem.components.ASearchField
import com.ae.alice.core.designsystem.components.APrimaryButton
import com.ae.alice.core.designsystem.theme.ADimensions
import com.ae.alice.core.domain.entity.Brand

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandsScreen(
    onBrandClick: (brandId: String, brandName: String) -> Unit,
    viewModel: BrandsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Car Brands") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Search field
            ASearchField(
                value = uiState.searchQuery,
                onValueChange = viewModel::onSearchQueryChange,
                placeholder = "Search brands...",
                onClear = viewModel::clearSearch,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ADimensions.ScreenPaddingHorizontal)
                    .padding(bottom = ADimensions.SpacingMd)
            )
            
            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                
                uiState.error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = uiState.error ?: "Error",
                                color = MaterialTheme.colorScheme.error
                            )
                            Spacer(modifier = Modifier.height(ADimensions.SpacingMd))
                            APrimaryButton(
                                text = "Retry",
                                onClick = viewModel::loadBrands
                            )
                        }
                    }
                }
                
                uiState.filteredBrands.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (uiState.searchQuery.isBlank()) "No brands found" else "No results for \"${uiState.searchQuery}\"",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                
                else -> {
                    LazyColumn(
                        contentPadding = PaddingValues(
                            horizontal = ADimensions.ScreenPaddingHorizontal,
                            vertical = ADimensions.SpacingSm
                        ),
                        verticalArrangement = Arrangement.spacedBy(ADimensions.SpacingSm)
                    ) {
                        items(
                            items = uiState.filteredBrands,
                            key = { it.id }
                        ) { brand ->
                            BrandItem(
                                brand = brand,
                                onClick = { onBrandClick(brand.id, brand.name) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BrandItem(
    brand: Brand,
    onClick: () -> Unit
) {
    ACard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ADimensions.CardPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Brand logo
            AsyncImage(
                model = brand.logoUrl,
                contentDescription = brand.name,
                modifier = Modifier
                    .size(ADimensions.BrandLogoSize)
                    .clip(CircleShape),
                contentScale = ContentScale.Fit
            )
            
            Spacer(modifier = Modifier.width(ADimensions.SpacingMd))
            
            // Brand info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = brand.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(ADimensions.SpacingXs))
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    brand.country?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    
                    if (brand.country != null && brand.foundedYear != null) {
                        Text(
                            text = " • ",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    
                    brand.foundedYear?.let {
                        Text(
                            text = "Est. $it",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(ADimensions.SpacingXs))
                
                Text(
                    text = "${brand.modelsCount} models",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
