package com.ae.alice.presentation.screens.models

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.BrokenImage
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
import coil3.compose.SubcomposeAsyncImage
import com.ae.alice.designsystem.components.ACard
import com.ae.alice.designsystem.components.AIcon
import com.ae.alice.designsystem.components.ASearchField
import com.ae.alice.designsystem.components.AText
import com.ae.alice.designsystem.components.appbar.AAppBar
import com.ae.alice.designsystem.components.indicator.ADotsProgressIndicator
import com.ae.alice.designsystem.components.scaffold.AScaffold
import com.ae.alice.designsystem.theme.ATheme
import com.ae.alice.domain.entity.CarModel
import org.koin.compose.viewmodel.koinViewModel

/**
 * Models screen — displays car models for a specific brand
 * with search and grid layout.
 */
@Composable
fun ModelsScreen(
    brandId: String,
    brandName: String,
    onBackClick: () -> Unit,
    onModelClick: (CarModel) -> Unit,
    viewModel: ModelsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(brandId) {
        viewModel.processIntent(ModelsIntent.LoadModels(brandId))
    }

    AScaffold(
        backgroundColor = ATheme.colorScheme.background.surface,
        topBar = {
            AAppBar(
                title = brandName,
                onLeadingClick = onBackClick,
                leadingContent = {
                    AIcon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = ATheme.colorScheme.shadePrimary
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Search bar
            ASearchField(
                value = state.searchQuery,
                onValueChange = { viewModel.processIntent(ModelsIntent.Search(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = ATheme.dimens.screenPaddingHorizontal,
                        vertical = ATheme.dimens.spacingSm
                    ),
                placeholder = "Search models...",
                onClear = { viewModel.processIntent(ModelsIntent.Search("")) }
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
                state.filteredModels.isEmpty() -> {
                    EmptyContent()
                }
                else -> {
                    ModelsGrid(
                        models = state.filteredModels,
                        onModelClick = onModelClick
                    )
                }
            }
        }
    }
}

@Composable
private fun ModelsGrid(
    models: List<CarModel>,
    onModelClick: (CarModel) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(ATheme.dimens.screenPaddingHorizontal),
        horizontalArrangement = Arrangement.spacedBy(ATheme.dimens.spacingMd),
        verticalArrangement = Arrangement.spacedBy(ATheme.dimens.spacingMd)
    ) {
        items(
            items = models,
            key = { it.id }
        ) { model ->
            ModelGridCard(
                model = model,
                onClick = { onModelClick(model) }
            )
        }
    }
}

@Composable
private fun ModelGridCard(
    model: CarModel,
    onClick: () -> Unit
) {
    ACard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ATheme.dimens.spacingMd),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(ATheme.dimens.spacingSm)
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
                    ADotsProgressIndicator(
                        colors = listOf(
                            ATheme.colorScheme.brand.brand,
                            ATheme.colorScheme.brand.brand.copy(alpha = 0.7f),
                            ATheme.colorScheme.brand.brand.copy(alpha = 0.4f),
                        ),
                        modifier = Modifier.padding(16.dp)
                    )
                },
                error = {
                    AIcon(
                        imageVector = Icons.Outlined.BrokenImage,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = ATheme.colorScheme.textDisabled
                    )
                }
            )

            // Model name
            AText(
                text = model.name,
                style = ATheme.typo.title.medium.copy(fontWeight = FontWeight.SemiBold),
                color = ATheme.colorScheme.shadePrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
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
            text = "No models available",
            style = ATheme.typo.body.medium,
            color = ATheme.colorScheme.shadeSecondary
        )
    }
}
