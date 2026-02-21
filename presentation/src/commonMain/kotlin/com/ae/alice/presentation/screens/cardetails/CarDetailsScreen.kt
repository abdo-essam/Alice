package com.ae.alice.presentation.screens.cardetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.outlined.BrokenImage
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.ae.alice.designsystem.components.APrimaryButton
import com.ae.alice.designsystem.theme.ATheme
import com.ae.alice.domain.entity.CarModel
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.car_details_about
import alice.presentation.generated.resources.car_details_back
import alice.presentation.generated.resources.car_details_error_default
import alice.presentation.generated.resources.car_details_get_car
import org.jetbrains.compose.resources.stringResource
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

    LaunchedEffect(modelId) {
        viewModel.processIntent(CarDetailsIntent.LoadModel(modelId))
    }

    Scaffold(
        containerColor = ATheme.colors.Light.Background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = modelName,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.car_details_back)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ATheme.colors.Light.Surface,
                    titleContentColor = ATheme.colors.Light.TextPrimary,
                    navigationIconContentColor = ATheme.colors.Secondary
                )
            )
        },
        bottomBar = {
            if (state.model != null) {
                APrimaryButton(
                    onClick = {
                        viewModel.processIntent(CarDetailsIntent.GetCar)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = ATheme.dimens.ScreenPaddingHorizontal,
                            vertical = ATheme.dimens.Spacing4xl
                        )
                ) {
                    Icon(
                        imageVector = Icons.Filled.DirectionsCar,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.size(ATheme.dimens.SpacingSm))
                    Text(
                        text = stringResource(Res.string.car_details_get_car),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    ) { paddingValues ->
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = ATheme.colors.Primary)
                }
            }

            state.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.error ?: stringResource(Res.string.car_details_error_default),
                        color = ATheme.colors.Error
                    )
                }
            }

            state.model != null -> {
                CarDetailsContent(
                    model = state.model!!,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Composable
private fun CarDetailsContent(
    model: CarModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Hero image with gradient overlay
        HeroImageSection(model = model)

        Spacer(modifier = Modifier.height(ATheme.dimens.ScreenPaddingVertical))

        // Description
        model.description?.let { desc ->
            Spacer(modifier = Modifier.height(ATheme.dimens.SpacingXl))
            HorizontalDivider(
                color = ATheme.colors.Light.Divider,
                modifier = Modifier.padding(horizontal = ATheme.dimens.ScreenPaddingHorizontal)
            )
            Spacer(modifier = Modifier.height(ATheme.dimens.SpacingXl))
            DescriptionSection(description = desc)
        }

        Spacer(modifier = Modifier.height(ATheme.dimens.Spacing3xl))
    }
}

@Composable
private fun HeroImageSection(model: CarModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.4f)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        ATheme.colors.Light.SurfaceVariant,
                        ATheme.colors.Light.Background
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        SubcomposeAsyncImage(
            model = model.imageUrl ?: "",
            contentDescription = model.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(ATheme.dimens.SpacingXxl),
            contentScale = ContentScale.Fit,
            loading = {
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp),
                    color = ATheme.colors.Primary,
                    strokeWidth = 3.dp
                )
            },
            error = {
                Icon(
                    imageVector = Icons.Outlined.BrokenImage,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = ATheme.colors.Light.TextDisabled
                )
            }
        )
    }
}

@Composable
private fun DescriptionSection(description: String) {
    Column(
        modifier = Modifier.padding(horizontal = ATheme.dimens.ScreenPaddingHorizontal),
        verticalArrangement = Arrangement.spacedBy(ATheme.dimens.SpacingSm)
    ) {
        Text(
            text = stringResource(Res.string.car_details_about),
            style = ATheme.typography.TitleMedium,
            fontWeight = FontWeight.SemiBold,
            color = ATheme.colors.Light.TextPrimary
        )
        Text(
            text = description,
            style = ATheme.typography.BodyMedium,
            color = ATheme.colors.Light.TextSecondary,
            lineHeight = 22.sp
        )
    }
}

