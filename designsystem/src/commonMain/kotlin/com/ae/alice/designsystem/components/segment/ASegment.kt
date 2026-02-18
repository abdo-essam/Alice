package com.ae.alice.designsystem.components.segment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ASegment(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    shape: Shape = RoundedCornerShape(50),
    indicatorHeight: Dp = 0.dp,
    indicatorColor: Color = MaterialTheme.colorScheme.primary,
    contentPadding: PaddingValues = PaddingValues(4.dp),
    content: ASegmentScope.() -> Unit,
) {
    val scope = remember { ASegmentScopeImpl() }.apply {
        items.clear()
        content()
    }

    var selectedIndex by remember { mutableStateOf(0) }

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(containerColor, shape)
                .padding(contentPadding),
        ) {
            scope.items.forEachIndexed { index, item ->
                ASegmentButton(
                    title = item.title,
                    isSelected = index == selectedIndex,
                    onClick = { selectedIndex = index },
                )
            }
        }

        if (indicatorHeight > 0.dp) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(indicatorHeight)
                    .background(indicatorColor)
            )
        }

        scope.items.getOrNull(selectedIndex)?.let { item ->
            item.content(scope)
        }
    }
}
