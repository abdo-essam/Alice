package com.ae.alice.presentation.screens.main

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.brands_empty
import alice.presentation.generated.resources.brands_error_default
import alice.presentation.generated.resources.brands_search_placeholder
import com.ae.alice.designsystem.components.card.GridCard
import com.ae.alice.designsystem.components.state.EmptyLayout
import com.ae.alice.designsystem.components.state.ErrorLayout
import com.ae.alice.designsystem.components.state.LoadingLayout
import com.ae.alice.designsystem.components.textfield.SearchField
import com.ae.alice.designsystem.theme.Theme
import com.ae.alice.domain.entity.Brand
import com.ae.alice.presentation.screens.brands.BrandsIntent
import com.ae.alice.presentation.screens.brands.BrandsViewModel
import com.ae.alice.presentation.screens.profile.ProfileScreen
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeBrandsTab(
    onBrandClick: (Brand) -> Unit,
    viewModel: BrandsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> LoadingLayout()
        state.error != null -> {
            ErrorLayout(
                title = state.error ?: stringResource(Res.string.brands_error_default),
                onRetry = { viewModel.processIntent(BrandsIntent.LoadBrands) }
            )
        }
        else -> {
            Column(modifier = Modifier.fillMaxSize()) {
                SearchField(
                    value = state.searchQuery,
                    onValueChange = { viewModel.processIntent(BrandsIntent.Search(it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = Theme.spacing._16,
                            vertical = Theme.spacing._8
                        ),
                    placeholder = stringResource(Res.string.brands_search_placeholder),
                    onClear = { viewModel.processIntent(BrandsIntent.Search("")) }
                )

                Spacer(modifier = Modifier.height(Theme.spacing._8))

                if (state.filteredBrands.isEmpty()) {
                    EmptyLayout(
                        title = stringResource(Res.string.brands_empty),
                    )
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(Theme.spacing._16),
                        horizontalArrangement = Arrangement.spacedBy(Theme.spacing._12),
                        verticalArrangement = Arrangement.spacedBy(Theme.spacing._12)
                    ) {
                        items(
                            items = state.filteredBrands,
                            key = { it.id }
                        ) { brand ->
                            GridCard(
                                imageUrl = brand.logoUrl ?: "",
                                title = brand.name,
                                onClick = { onBrandClick(brand) }
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ArchiveTab() {
    EmptyLayout(
        title = "الأرشيف",
        message = "لا توجد عناصر محفوظة بعد",
    )
}

@Composable
fun ProfileTab() {
    ProfileScreen()
}
