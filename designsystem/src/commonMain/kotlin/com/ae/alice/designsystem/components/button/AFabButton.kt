package com.ae.alice.designsystem.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.AIcon
import com.ae.alice.designsystem.theme.ADimensions

@Composable
fun AFabButton(
    painter: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    iconSize: Dp = 24.dp,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    shape: Shape = RoundedCornerShape(ADimensions.RadiusMd),
) {
    AButton(
        onClick = onClick,
        containerColor = containerColor,
        contentColor = contentColor,
        shape = shape,
        contentPadding = contentPadding,
        modifier = modifier,
    ) {
        AIcon(
            painter = painter,
            tint = it,
            contentDescription = contentDescription,
            modifier = Modifier.size(iconSize)
        )
    }
}
