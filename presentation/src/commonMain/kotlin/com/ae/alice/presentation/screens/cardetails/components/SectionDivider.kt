package com.ae.alice.presentation.screens.cardetails.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae.alice.designsystem.theme.ATheme

@Composable
fun SectionDivider(
    modifier: Modifier = Modifier
) {
    HorizontalDivider(
        color = ATheme.colors.Light.Divider,
        modifier = modifier
            .padding(horizontal = ATheme.dimens.ScreenPaddingHorizontal)
    )
}
