package com.ae.alice.presentation.screens.cardetails.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae.alice.designsystem.theme.Theme
import com.ae.alice.domain.entity.CarModel

@Composable
fun CarDetailsContent(
    model: CarModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HeroImageSection(
            imageUrl = model.imageUrl,
            contentDescription = model.name
        )

        Spacer(modifier = Modifier.height(Theme.spacing._16))

        model.description?.let { description ->
            Spacer(modifier = Modifier.height(Theme.spacing._20))

            SectionDivider()

            Spacer(modifier = Modifier.height(Theme.spacing._20))

            DescriptionSection(description = description)
        }

        Spacer(modifier = Modifier.height(Theme.spacing._40))
    }
}
