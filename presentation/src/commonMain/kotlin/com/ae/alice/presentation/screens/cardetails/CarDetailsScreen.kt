package com.ae.alice.presentation.screens.cardetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ae.alice.designsystem.components.scaffold.Scaffold
import com.ae.alice.designsystem.components.state.ErrorLayout
import com.ae.alice.designsystem.components.state.LoadingLayout
import com.ae.alice.designsystem.theme.Theme
import com.ae.alice.presentation.screens.cardetails.components.CarDetailsAppBar
import com.ae.alice.presentation.screens.cardetails.components.CarDetailsBottomBar
import com.ae.alice.presentation.screens.cardetails.components.CarDetailsContent
import org.koin.compose.viewmodel.koinViewModel

/**
 * Car details screen — uses MENA Scaffold with AppBar back navigation,
 * LoadingLayout, ErrorLayout, and a sticky bottom action bar.
 */
@Composable
fun CarDetailsScreen(
    modelId: String,
    modelName: String,
    onBackClick: () -> Unit,
    onGetCarClick: () -> Unit,
    viewModel: CarDetailsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(modelId) {
        viewModel.processIntent(CarDetailsIntent.LoadModel(modelId))
    }

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is CarDetailsEffect.NavigateToGetCar -> onGetCarClick()
            }
        }
    }

    Scaffold(
        backgroundColor = Theme.colorScheme.background.surface,
        topBar = {
            CarDetailsAppBar(
                title = modelName,
                isSaved = state.model?.isFavorite == true,
                onBackClick = onBackClick,
                onSaveClick = { viewModel.processIntent(CarDetailsIntent.ToggleSave) }
            )
        },
        bottomBar = {
            if (state.isContentReady) {
                CarDetailsBottomBar(
                    onGetCarClick = {
                        viewModel.processIntent(CarDetailsIntent.GetCar)
                    }
                )
            }
        }
    ) {
        when {
            state.isLoading -> LoadingLayout()
            state.hasError -> {
                ErrorLayout(
                    title = state.error ?: "",
                    onRetry = { viewModel.processIntent(CarDetailsIntent.Retry) }
                )
            }
            state.isContentReady -> {
                CarDetailsContent(
                    model = state.model!!
                )
            }
        }
    }
}
