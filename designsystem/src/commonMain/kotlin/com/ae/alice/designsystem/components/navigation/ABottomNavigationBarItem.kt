package com.ae.alice.designsystem.components.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.AIcon
import com.ae.alice.designsystem.components.AText

@Composable
internal fun ABottomNavigationBarItem(
    item: ABottomNavigationItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
) {
    val animatedTint by animateColorAsState(
        targetValue = if (isSelected) selectedColor else unselectedColor
    )

    Column(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        AIcon(
            painter = if (isSelected) item.selectedIcon else item.notSelectedIcon,
            contentDescription = item.title,
            modifier = Modifier.size(24.dp),
            tint = animatedTint,
        )
        AText(
            text = item.title,
            style = MaterialTheme.typography.labelSmall,
            color = animatedTint,
        )
    }
}
