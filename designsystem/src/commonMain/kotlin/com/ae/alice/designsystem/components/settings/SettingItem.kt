package com.ae.alice.designsystem.components.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import com.ae.alice.designsystem.components.icon.Icon
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme

/**
 * MENA-style settings row — leading icon, title, optional trailing text.
 * Used in profile screen for account settings, app settings, and other sections.
 */
@Composable
fun SettingItem(
    title: String,
    leadingIcon: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    trailingText: String? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Theme.radius.lg))
            .background(Theme.colorScheme.primary.primary.copy(alpha = 0.08f))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = Theme.spacing._12),
        horizontalArrangement = Arrangement.spacedBy(Theme.spacing._8),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(vertical = Theme.spacing._12)
                .size(Theme.spacing._24),
            painter = leadingIcon,
            tint = Theme.colorScheme.primary.primary,
            contentDescription = null,
        )
        Text(
            text = title,
            style = Theme.typography.label.medium,
            color = Theme.colorScheme.shadePrimary,
            modifier = Modifier.weight(1f)
        )
        trailingText?.let {
            Text(
                text = it,
                style = Theme.typography.label.small,
                color = Theme.colorScheme.shadeTertiary,
            )
        }
    }
}
