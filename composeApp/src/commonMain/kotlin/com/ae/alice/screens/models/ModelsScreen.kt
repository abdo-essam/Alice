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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.ae.alice.designsystem.theme.AColors
import com.ae.alice.domain.entity.CarModel
import com.ae.alice.presentation.screens.models.ModelsIntent
import com.ae.alice.presentation.screens.models.ModelsViewModel
import org.koin.compose.viewmodel.koinViewModel

/**
 * Models screen displaying car models for a specific brand.
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
        when {
            state.isLoading -> {
                LoadingContent(paddingValues)
            }
            state.error != null -> {
                ErrorContent(paddingValues, state.error ?: "An error occurred")
            }
            state.models.isEmpty() -> {
                EmptyContent(paddingValues)
            }
            else -> {
                ModelsList(
                    paddingValues = paddingValues,
                    models = state.models
                )
            }
        }
    }
}

@Composable
private fun ModelsList(
    paddingValues: PaddingValues,
    models: List<CarModel>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = models,
            key = { it.id }
        ) { model ->
            ModelCard(model = model)
        }
    }
}

@Composable
private fun ModelCard(model: CarModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = AColors.Light.Surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = model.imageUrl ?: "",
                contentDescription = model.name,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = model.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AColors.Light.TextPrimary
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "${model.year ?: ""} • ${model.category ?: "N/A"}",
                    fontSize = 13.sp,
                    color = AColors.Light.TextSecondary
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "${model.horsepower ?: 0} HP",
                    fontSize = 12.sp,
                    color = AColors.Primary,
                    fontWeight = FontWeight.Medium
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = model.formattedPrice ?: "Price N/A",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = AColors.Secondary
                )
            }
        }
    }
}

@Composable
private fun LoadingContent(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = AColors.Primary)
    }
}

@Composable
private fun ErrorContent(paddingValues: PaddingValues, message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Text(text = message, color = AColors.Error)
    }
}

@Composable
private fun EmptyContent(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No models available",
            color = AColors.Light.TextSecondary
        )
    }
}
