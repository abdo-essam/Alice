package com.ae.alice.presentation.screens.cardetails.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae.alice.designsystem.theme.ATheme
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

        Spacer(modifier = Modifier.height(ATheme.dimens.ScreenPaddingVertical))

        model.description?.let { description ->
            Spacer(modifier = Modifier.height(ATheme.dimens.SpacingXl))

            SectionDivider()

            Spacer(modifier = Modifier.height(ATheme.dimens.SpacingXl))

            DescriptionSection(description = description)
        }

        // Extra spacing at the bottom so content doesn't hide behind bottom bar
        Spacer(modifier = Modifier.height(ATheme.dimens.Spacing4xl))
    }
}
