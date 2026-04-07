package com.ae.alice.designsystem.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.button.content.BaseButtonContent
import com.ae.alice.designsystem.theme.Theme

@Composable
fun TextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    trailingIcon: Painter? = null,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    contentColor: Color = Theme.colorScheme.brand.brand,
    disabledContentColor: Color = Theme.colorScheme.disabled,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    iconSize: Dp = 16.dp,
    iconStartPadding: Dp = Theme.spacing._4,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    Button(
        onClick = onClick,
        isEnabled = isEnabled,
        contentPadding = contentPadding,
        contentColor = contentColor,
        disabledContentColor = disabledContentColor,
        shape = RoundedCornerShape(Theme.radius.xxs),
        isLoading = isLoading,
        loadingColors = listOf(
            Theme.colorScheme.stroke,
            Theme.colorScheme.shadeTertiary,
            Theme.colorScheme.brand.brand
        ),
        modifier = modifier
    ) {
        BaseButtonContent(
            text = text,
            trailingIcon = trailingIcon,
            iconSize = iconSize,
            iconStartPadding = iconStartPadding,
            overflow = overflow,
            contentColor = it,
        )
    }
}
