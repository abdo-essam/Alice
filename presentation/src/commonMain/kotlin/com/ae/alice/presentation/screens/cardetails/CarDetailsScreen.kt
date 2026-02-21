package com.ae.alice.presentation.screens.cardetails

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.ae.alice.designsystem.theme.ATheme
import com.ae.alice.presentation.screens.cardetails.components.CarDetailsBottomBar
import com.ae.alice.presentation.screens.cardetails.components.CarDetailsContent
import com.ae.alice.presentation.screens.cardetails.components.CarDetailsErrorView
import com.ae.alice.presentation.screens.cardetails.components.CarDetailsLoadingIndicator
import com.ae.alice.presentation.screens.cardetails.components.CarDetailsTopBar
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarDetailsScreen(
    modelId: String,
    modelName: String,
    onBackClick: () -> Unit,
    viewModel: CarDetailsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    LaunchedEffect(modelId) {
        viewModel.processIntent(CarDetailsIntent.LoadModel(modelId))
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = ATheme.colors.Light.Background,
        topBar = {
            CarDetailsTopBar(
                title = modelName,
                onBackClick = onBackClick,
                scrollBehavior = scrollBehavior
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
    ) { paddingValues ->
        CarDetailsBody(
            state = state,
            onRetry = {
                viewModel.processIntent(CarDetailsIntent.Retry)
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}

@Composable
private fun CarDetailsBody(
    state: CarDetailsState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        state.isLoading -> {
            CarDetailsLoadingIndicator(modifier = modifier)
        }

        state.hasError -> {
            CarDetailsErrorView(
                errorMessage = state.error,
                onRetryClick = onRetry,
                modifier = modifier
            )
        }

        state.isContentReady -> {
            CarDetailsContent(
                model = state.model!!,
                modifier = modifier
            )
        }
    }
}

