package com.ae.alice.screens.models

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.BrokenImage
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.ae.alice.designsystem.components.ASearchField
import com.ae.alice.designsystem.theme.AColors
import com.ae.alice.domain.entity.CarModel
import com.ae.alice.presentation.screens.models.ModelsIntent
import com.ae.alice.presentation.screens.models.ModelsViewModel
import org.koin.compose.viewmodel.koinViewModel

/**
 * Models screen displaying car models for a specific brand
 * with title, search bar, and grid layout.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModelsScreen(
    brandId: String,
    brandName: String,
    onBackClick: () -> Unit,
    viewModel: ModelsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(brandId) {
        viewModel.processIntent(ModelsIntent.LoadModels(brandId))
    }

    Scaffold(
        containerColor = AColors.Light.Background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = brandName,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AColors.Light.Surface,
                    titleContentColor = AColors.Light.TextPrimary,
                    navigationIconContentColor = AColors.Secondary
                )
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
                onValueChange = { viewModel.processIntent(ModelsIntent.Search(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = "Search models...",
                onClear = { viewModel.processIntent(ModelsIntent.Search("")) }
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
                state.filteredModels.isEmpty() -> {
                    EmptyContent()
                }
                else -> {
                    ModelsGrid(models = state.filteredModels)
                }
            }
        }
    }
}

@Composable
private fun ModelsGrid(models: List<CarModel>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = models,
            key = { it.id }
        ) { model ->
            ModelGridCard(model = model)
        }
    }
}

@Composable
private fun ModelGridCard(model: CarModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = AColors.Light.Surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp,
            pressedElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Model image
            SubcomposeAsyncImage(
                model = model.imageUrl ?: "",
                contentDescription = model.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.2f),
                contentScale = ContentScale.Fit,
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = AColors.Primary,
                        strokeWidth = 2.dp
                    )
                },
                error = {
                    Icon(
                        imageVector = Icons.Outlined.BrokenImage,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = AColors.Light.TextDisabled
                    )
                }
            )

            // Model name
            Text(
                text = model.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = AColors.Light.TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            // Year & category
            if (model.year != null || model.category != null) {
                Text(
                    text = listOfNotNull(
                        model.year?.toString(),
                        model.category
                    ).joinToString(" • "),
                    fontSize = 12.sp,
                    color = AColors.Light.TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Price
            model.formattedPrice?.let { price ->
                Text(
                    text = price,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = AColors.Primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
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
        Text(text = message, color = AColors.Error)
    }
}

@Composable
private fun EmptyContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No models available",
            color = AColors.Light.TextSecondary
        )
    }
}
