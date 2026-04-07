package com.ae.alice.presentation.screens.cardetails.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae.alice.designsystem.theme.Theme

@Composable
fun SectionDivider(
    modifier: Modifier = Modifier
) {
    HorizontalDivider(
        color = Theme.colorScheme.stroke,
        modifier = modifier
            .padding(horizontal = Theme.spacing._16)
    )
}
