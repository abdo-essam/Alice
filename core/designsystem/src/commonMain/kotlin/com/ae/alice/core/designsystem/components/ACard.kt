package com.ae.alice.core.designsystem.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ae.alice.core.designsystem.theme.ADimensions
import com.ae.alice.core.designsystem.theme.ATheme

/**
 * Standard card.
 */
@Composable
fun ACard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    if (onClick != null) {
        Card(
            onClick = onClick,
            modifier = modifier,
            shape = RoundedCornerShape(ADimensions.RadiusMd),
            colors = CardDefaults.cardColors(
                containerColor = ATheme.extendedColors.cardBackground
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = ADimensions.CardElevation
            )
        ) {
            content()
        }
    } else {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(ADimensions.RadiusMd),
            colors = CardDefaults.cardColors(
                containerColor = ATheme.extendedColors.cardBackground
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = ADimensions.CardElevation
            )
        ) {
            content()
        }
    }
}
