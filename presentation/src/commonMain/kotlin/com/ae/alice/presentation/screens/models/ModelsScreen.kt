package com.ae.alice.presentation.screens.models

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.ic_arrow_left
import alice.presentation.generated.resources.models_empty
import alice.presentation.generated.resources.models_error_default
import alice.presentation.generated.resources.models_search_placeholder
import com.ae.alice.designsystem.components.appBar.AppBar
import com.ae.alice.designsystem.components.card.GridCard
import com.ae.alice.designsystem.components.icon.Icon
import com.ae.alice.designsystem.components.scaffold.Scaffold
import com.ae.alice.designsystem.components.state.EmptyLayout
import com.ae.alice.designsystem.components.state.ErrorLayout
import com.ae.alice.designsystem.components.state.LoadingLayout
import com.ae.alice.designsystem.components.textfield.SearchField
import com.ae.alice.designsystem.theme.Theme
import com.ae.alice.domain.entity.CarModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

/**
 * Models screen — inner screen, no bottom nav.
 * Uses MENA Scaffold, AppBar with back arrow, LoadingLayout, ErrorLayout.
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

    Scaffold(
        backgroundColor = Theme.colorScheme.background.surfaceLow,
        topBar = {
            AppBar(
                title = brandName,
                leadingContent = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_left),
                        contentDescription = "Back",
                        tint = Theme.colorScheme.primary.primary
                    )
                },
                onLeadingClick = onBackClick
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Search bar
            SearchField(
                value = state.searchQuery,
                onValueChange = { viewModel.processIntent(ModelsIntent.Search(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = Theme.spacing._16,
                        vertical = Theme.spacing._8
                    ),
                placeholder = stringResource(Res.string.models_search_placeholder),
                onClear = { viewModel.processIntent(ModelsIntent.Search("")) }
            )

            Spacer(modifier = Modifier.height(Theme.spacing._8))

            // Content
            when {
                state.isLoading -> LoadingLayout()
                state.error != null -> {
                    ErrorLayout(
                        title = state.error ?: stringResource(Res.string.models_error_default),
                        onRetry = { viewModel.processIntent(ModelsIntent.LoadModels(brandId)) }
                    )
                }
                state.filteredModels.isEmpty() -> {
                    EmptyLayout(
                        title = stringResource(Res.string.models_empty),
                    )
                }
                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(Theme.spacing._16),
                        horizontalArrangement = Arrangement.spacedBy(Theme.spacing._12),
                        verticalArrangement = Arrangement.spacedBy(Theme.spacing._12)
                    ) {
                        items(
                            items = state.filteredModels,
                            key = { it.id }
                        ) { model ->
                            GridCard(
                                imageUrl = model.imageUrl ?: "",
                                title = model.name,
                                onClick = { onModelClick(model) }
                            )
                        }
                    }
                }
            }
        }
    }
}
