package com.ae.alice.screens.models

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.ae.alice.core.designsystem.components.APrimaryButton
import com.ae.alice.core.designsystem.theme.ADimensions
import com.ae.alice.core.domain.entity.CarModel

/**
 * Models screen showing all car models for a brand.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModelsScreen(
    brandId: String,
    brandName: String,
    onBackClick: () -> Unit,
    viewModel: ModelsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    LaunchedEffect(brandId) {
        viewModel.loadModels(brandId)
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(brandName) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            
            uiState.error != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
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
                            onClick = { viewModel.loadModels(brandId) }
                        )
                    }
                }
            }
            
            uiState.models.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No models available",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            else -> {
                LazyColumn(
                    modifier = Modifier.padding(paddingValues),
                    contentPadding = PaddingValues(
                        horizontal = ADimensions.ScreenPaddingHorizontal,
                        vertical = ADimensions.SpacingSm
                    ),
                    verticalArrangement = Arrangement.spacedBy(ADimensions.SpacingMd)
                ) {
                    items(
                        items = uiState.models,
                        key = { it.id }
                    ) { model ->
                        ModelItem(model = model)
                    }
                }
            }
        }
    }
}

@Composable
private fun ModelItem(model: CarModel) {
    ACard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Model image
            model.imageUrl?.let { url ->
                AsyncImage(
                    model = url,
                    contentDescription = model.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ADimensions.ModelImageHeight)
                        .clip(RoundedCornerShape(topStart = ADimensions.RadiusMd, topEnd = ADimensions.RadiusMd)),
                    contentScale = ContentScale.Crop
                )
            }
            
            // Model info
            Column(
                modifier = Modifier.padding(ADimensions.CardPadding)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = model.name,
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    
                    model.year?.let {
                        Text(
                            text = it.toString(),
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(ADimensions.SpacingSm))
                
                // Category and Engine
                Row {
                    model.category?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    
                    if (model.category != null && model.engineType != null) {
                        Text(
                            text = " • ",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    
                    model.engineType?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(ADimensions.SpacingXs))
                
                // Horsepower and Transmission
                Row {
                    model.horsepower?.let {
                        Text(
                            text = "$it HP",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    
                    if (model.horsepower != null && model.transmission != null) {
                        Text(
                            text = " • ",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    
                    model.transmission?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                
                // Price
                model.formattedPrice?.let { price ->
                    Spacer(modifier = Modifier.height(ADimensions.SpacingSm))
                    Text(
                        text = price,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
