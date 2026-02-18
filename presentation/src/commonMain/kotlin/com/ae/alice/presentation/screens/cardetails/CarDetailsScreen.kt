package com.ae.alice.presentation.screens.cardetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.BrokenImage
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.ElectricBolt
import androidx.compose.material.icons.outlined.LocalGasStation
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Speed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.ae.alice.designsystem.components.ACard
import com.ae.alice.designsystem.components.ADivider
import com.ae.alice.designsystem.components.AIcon
import com.ae.alice.designsystem.components.AText
import com.ae.alice.designsystem.components.appbar.AAppBar
import com.ae.alice.designsystem.components.indicator.ADotsProgressIndicator
import com.ae.alice.designsystem.components.scaffold.AScaffold
import com.ae.alice.designsystem.theme.ATheme
import com.ae.alice.domain.entity.CarModel
import org.koin.compose.viewmodel.koinViewModel

/**
 * Car Details screen showing full specifications of a car model.
 */
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

    AScaffold(
        backgroundColor = ATheme.colorScheme.background.surface,
        topBar = {
            AAppBar(
                title = modelName,
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
        when {
            state.isLoading -> {
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
            state.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    AText(
                        text = state.error ?: "An error occurred",
                        style = ATheme.typo.body.medium,
                        color = ATheme.colorScheme.error
                    )
                }
            }
            state.model != null -> {
                CarDetailsContent(model = state.model!!)
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
        // Hero image
        HeroImageSection(model = model)

        Spacer(modifier = Modifier.height(ATheme.dimens.spacingLg))

        // Brand & year badge
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = ATheme.dimens.screenPaddingHorizontal),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AText(
                text = model.brandName,
                style = ATheme.typo.title.medium.copy(fontWeight = FontWeight.Medium),
                color = ATheme.colorScheme.shadePrimary
            )
            model.year?.let { year ->
                AText(
                    text = year.toString(),
                    style = ATheme.typo.label.large,
                    color = ATheme.colorScheme.brand.brand,
                    modifier = Modifier
                        .background(
                            color = ATheme.colorScheme.brand.brand.copy(alpha = 0.12f),
                            shape = RoundedCornerShape(ATheme.dimens.radiusFull)
                        )
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                )
            }
        }

        // Price
        model.formattedPrice?.let { price ->
            Spacer(modifier = Modifier.height(ATheme.dimens.spacingMd))
            AText(
                text = price,
                style = ATheme.typo.headline.medium.copy(fontWeight = FontWeight.Bold),
                color = ATheme.colorScheme.brand.brand,
                modifier = Modifier.padding(horizontal = ATheme.dimens.screenPaddingHorizontal)
            )
        }

        Spacer(modifier = Modifier.height(ATheme.dimens.spacingXl))

        // Specs grid
        SpecsSection(model = model)

        // Description
        model.description?.let { desc ->
            Spacer(modifier = Modifier.height(ATheme.dimens.spacingXl))
            ADivider(
                modifier = Modifier.padding(horizontal = ATheme.dimens.screenPaddingHorizontal)
            )
            Spacer(modifier = Modifier.height(ATheme.dimens.spacingXl))
            DescriptionSection(description = desc)
        }

        Spacer(modifier = Modifier.height(ATheme.dimens.spacing3xl))
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
                        ATheme.colorScheme.background.surfaceHigh,
                        ATheme.colorScheme.background.surface
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
                .padding(ATheme.dimens.spacingXxl),
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
                    modifier = Modifier.size(64.dp),
                    tint = ATheme.colorScheme.textDisabled
                )
            }
        )
    }
}

@Composable
private fun SpecsSection(model: CarModel) {
    Column(
        modifier = Modifier.padding(horizontal = ATheme.dimens.screenPaddingHorizontal),
        verticalArrangement = Arrangement.spacedBy(ATheme.dimens.spacingMd)
    ) {
        AText(
            text = "Specifications",
            style = ATheme.typo.title.medium.copy(fontWeight = FontWeight.SemiBold),
            color = ATheme.colorScheme.shadePrimary
        )

        Spacer(modifier = Modifier.height(ATheme.dimens.spacingXs))

        // Specs in 2-column grid
        val specs = buildList {
            model.category?.let { add(SpecItem(Icons.Outlined.Category, "Category", it)) }
            model.year?.let { add(SpecItem(Icons.Outlined.CalendarMonth, "Year", it.toString())) }
            model.engineType?.let { add(SpecItem(Icons.Outlined.LocalGasStation, "Engine", it)) }
            model.transmission?.let { add(SpecItem(Icons.Outlined.Settings, "Transmission", it)) }
            model.horsepower?.let { add(SpecItem(Icons.Outlined.Speed, "Horsepower", "${it} HP")) }
            model.formattedPrice?.let { add(SpecItem(Icons.Outlined.ElectricBolt, "Price", it)) }
        }

        specs.chunked(2).forEach { rowSpecs ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(ATheme.dimens.spacingMd)
            ) {
                rowSpecs.forEach { spec ->
                    SpecCard(
                        spec = spec,
                        modifier = Modifier.weight(1f)
                    )
                }
                if (rowSpecs.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

private data class SpecItem(
    val icon: ImageVector,
    val label: String,
    val value: String
)

@Composable
private fun SpecCard(
    spec: SpecItem,
    modifier: Modifier = Modifier
) {
    ACard(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ATheme.dimens.spacingLg),
            verticalArrangement = Arrangement.spacedBy(ATheme.dimens.spacingSm)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(ATheme.dimens.spacingSm)
            ) {
                AIcon(
                    imageVector = spec.icon,
                    contentDescription = null,
                    tint = ATheme.colorScheme.brand.brand,
                    modifier = Modifier.size(ATheme.dimens.iconSm)
                )
                AText(
                    text = spec.label,
                    style = ATheme.typo.label.medium,
                    color = ATheme.colorScheme.shadeSecondary
                )
            }
            AText(
                text = spec.value,
                style = ATheme.typo.title.small.copy(fontWeight = FontWeight.SemiBold),
                color = ATheme.colorScheme.shadePrimary
            )
        }
    }
}

@Composable
private fun DescriptionSection(description: String) {
    Column(
        modifier = Modifier.padding(horizontal = ATheme.dimens.screenPaddingHorizontal),
        verticalArrangement = Arrangement.spacedBy(ATheme.dimens.spacingSm)
    ) {
        AText(
            text = "About",
            style = ATheme.typo.title.medium.copy(fontWeight = FontWeight.SemiBold),
            color = ATheme.colorScheme.shadePrimary
        )
        AText(
            text = description,
            style = ATheme.typo.body.medium.copy(lineHeight = 22.sp),
            color = ATheme.colorScheme.shadeSecondary
        )
    }
}
