package com.ae.alice.designsystem.components

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.ae.alice.designsystem.theme.ADimensions
import com.ae.alice.designsystem.theme.ATheme

@Composable
fun ADivider(
    modifier: Modifier = Modifier,
    thickness: Dp = ADimensions.DividerThickness,
    color: Color = ATheme.extendedColors.divider,
) {
    HorizontalDivider(
        modifier = modifier,
        thickness = thickness,
        color = color,
    )
}
