package com.ae.alice.designsystem.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.theme.Theme

@Composable
fun FabButton(
    painter: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    iconSize: Dp = 24.dp,
    containerColor: Color = Theme.colorScheme.brand.brand,
    contentColor: Color = Theme.colorScheme.brand.onBrand,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    shape: Shape = RoundedCornerShape(Theme.radius.md)
) {
    Button(
        onClick = onClick,
        containerColor = containerColor,
        contentColor = contentColor,
        shape = shape,
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        Icon(
            painter = painter,
            tint = it,
            contentDescription = contentDescription,
            modifier = Modifier.size(iconSize)
        )
    }
}
