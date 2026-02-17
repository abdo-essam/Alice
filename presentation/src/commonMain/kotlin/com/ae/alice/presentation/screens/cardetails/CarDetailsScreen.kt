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
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.ae.alice.designsystem.theme.ATheme
import com.ae.alice.domain.entity.CarModel
import org.koin.compose.viewmodel.koinViewModel

/**
 * Car Details screen showing full specifications of a car model.
 */
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
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ATheme.colors.Light.Surface,
                    titleContentColor = ATheme.colors.Light.TextPrimary,
                    navigationIconContentColor = ATheme.colors.Secondary
                )
            )
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
                        text = state.error ?: "An error occurred",
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

      /*  // Brand & year badge
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = ATheme.dimens.ScreenPaddingHorizontal),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = model.brandName,
                style = ATheme.typography.TitleMedium,
                color = ATheme.colors.Secondary,
                fontWeight = FontWeight.Medium
            )
            model.year?.let { year ->
                Text(
                    text = year.toString(),
                    style = ATheme.typography.LabelLarge,
                    color = ATheme.colors.Primary,
                    modifier = Modifier
                        .background(
                            color = ATheme.colors.Primary.copy(alpha = 0.12f),
                            shape = RoundedCornerShape(ATheme.dimens.RadiusFull)
                        )
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                )
            }
        }

        // Price
        model.formattedPrice?.let { price ->
            Spacer(modifier = Modifier.height(ATheme.dimens.SpacingMd))
            Text(
                text = price,
                style = ATheme.typography.HeadlineSmall,
                color = ATheme.colors.Primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = ATheme.dimens.ScreenPaddingHorizontal)
            )
        }

        Spacer(modifier = Modifier.height(ATheme.dimens.SpacingXl))

        // Specs grid
        SpecsSection(model = model)*/

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
private fun SpecsSection(model: CarModel) {
    Column(
        modifier = Modifier.padding(horizontal = ATheme.dimens.ScreenPaddingHorizontal),
        verticalArrangement = Arrangement.spacedBy(ATheme.dimens.SpacingMd)
    ) {
        Text(
            text = "Specifications",
            style = ATheme.typography.TitleMedium,
            fontWeight = FontWeight.SemiBold,
            color = ATheme.colors.Light.TextPrimary
        )

        Spacer(modifier = Modifier.height(ATheme.dimens.SpacingXs))

        // Specs grid - 2 columns
        val specs = buildList {
            model.category?.let { add(SpecItem(Icons.Outlined.Category, "Category", it)) }
            model.year?.let { add(SpecItem(Icons.Outlined.CalendarMonth, "Year", it.toString())) }
            model.engineType?.let { add(SpecItem(Icons.Outlined.LocalGasStation, "Engine", it)) }
            model.transmission?.let { add(SpecItem(Icons.Outlined.Settings, "Transmission", it)) }
            model.horsepower?.let { add(SpecItem(Icons.Outlined.Speed, "Horsepower", "${it} HP")) }
            model.formattedPrice?.let { add(SpecItem(Icons.Outlined.ElectricBolt, "Price", it)) }
        }

        // Render specs in rows of 2
        specs.chunked(2).forEach { rowSpecs ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(ATheme.dimens.SpacingMd)
            ) {
                rowSpecs.forEach { spec ->
                    SpecCard(
                        spec = spec,
                        modifier = Modifier.weight(1f)
                    )
                }
                // Fill empty space if odd number
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
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(ATheme.dimens.RadiusMd),
        colors = CardDefaults.cardColors(
            containerColor = ATheme.colors.Light.Surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ATheme.dimens.SpacingLg),
            verticalArrangement = Arrangement.spacedBy(ATheme.dimens.SpacingSm)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(ATheme.dimens.SpacingSm)
            ) {
                Icon(
                    imageVector = spec.icon,
                    contentDescription = null,
                    tint = ATheme.colors.Primary,
                    modifier = Modifier.size(ATheme.dimens.IconSm)
                )
                Text(
                    text = spec.label,
                    style = ATheme.typography.LabelMedium,
                    color = ATheme.colors.Light.TextSecondary
                )
            }
            Text(
                text = spec.value,
                style = ATheme.typography.TitleSmall,
                fontWeight = FontWeight.SemiBold,
                color = ATheme.colors.Light.TextPrimary
            )
        }
    }
}

@Composable
private fun DescriptionSection(description: String) {
    Column(
        modifier = Modifier.padding(horizontal = ATheme.dimens.ScreenPaddingHorizontal),
        verticalArrangement = Arrangement.spacedBy(ATheme.dimens.SpacingSm)
    ) {
        Text(
            text = "About",
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
