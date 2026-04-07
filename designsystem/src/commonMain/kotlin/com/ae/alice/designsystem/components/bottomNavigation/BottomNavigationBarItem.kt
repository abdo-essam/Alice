package com.ae.alice.designsystem.components.bottomNavigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.icon.Icon
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme

@Composable
fun BottomNavigationBarItem(
    isSelected: Boolean,
    item: BottomNavigationItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val painter = resolveIconPainter(item, isSelected)
    val animatedIconTint by animateColorAsState(
        targetValue = if (isSelected) Theme.colorScheme.brand.brand else Theme.colorScheme.shadeSecondary,
    )
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .height(76.dp)
            .then(
                if (isSelected) Modifier
                else Modifier.clickable(
                    onClick = onClick,
                    indication = null,
                    interactionSource = interactionSource
                )
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(alignment = Alignment.TopCenter)
        ) {
            Icon(
                painter = painter,
                modifier = Modifier.size(24.dp),
                contentDescription = item.title,
                tint = animatedIconTint
            )

            if (isSelected) {
                Text(
                    text = item.title,
                    style = Theme.typography.label.medium,
                    color = Theme.colorScheme.brand.brand,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun resolveIconPainter(item: BottomNavigationItem, isSelected: Boolean): Painter {
    return if (item.painterIcon != null && item.painterSelectedIcon != null) {
        if (isSelected) item.painterSelectedIcon else item.painterIcon
    } else if (item.vectorIcon != null && item.vectorSelectedIcon != null) {
        val vector = if (isSelected) item.vectorSelectedIcon else item.vectorIcon
        rememberVectorPainter(vector)
    } else {
        error("BottomNavigationItem must have either painter or vector icons set")
    }
}
