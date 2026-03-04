package com.ae.alice.designsystem.components.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.ae.alice.designsystem.components.button.TextButton
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme

/**
 * Section header with a title and an action button.
 *
 * Commonly used for "See all →" patterns in list screens.
 */
@Composable
fun Section(
    title: String,
    actionName: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    trailingIcon: Painter? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Text(
            text = title,
            style = Theme.typography.title.small,
            modifier = Modifier.weight(1f)
        )

        TextButton(
            text = actionName,
            trailingIcon = trailingIcon,
            onClick = onClick
        )
    }
}
