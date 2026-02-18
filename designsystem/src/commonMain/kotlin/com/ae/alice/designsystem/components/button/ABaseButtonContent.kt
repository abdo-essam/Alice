package com.ae.alice.designsystem.components.button

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.AIcon
import com.ae.alice.designsystem.components.AText
import androidx.compose.material3.MaterialTheme

@Composable
internal fun ABaseButtonContent(
    text: String? = null,
    trailingIcon: Painter? = null,
    contentDescription: String? = null,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    iconSize: Dp = 20.dp,
    iconStartPadding: Dp = 8.dp,
    overflow: TextOverflow = TextOverflow.Ellipsis,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        text?.let {
            AText(
                text = it,
                style = MaterialTheme.typography.labelMedium,
                color = contentColor,
                overflow = overflow,
                maxLines = 1,
            )
        }
        trailingIcon?.let { icon ->
            Spacer(modifier = Modifier.width(iconStartPadding))
            AIcon(
                painter = icon,
                contentDescription = contentDescription,
                tint = contentColor,
                modifier = Modifier.size(iconSize)
            )
        }
    }
}
