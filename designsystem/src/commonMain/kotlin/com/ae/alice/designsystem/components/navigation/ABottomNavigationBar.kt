package com.ae.alice.designsystem.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ABottomNavigationBar(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    selectedIndex: Int = 0,
    content: ABottomNavigationScope.() -> Unit,
) {
    val scope = remember { ABottomNavigationScopeImpl() }.apply {
        items.clear()
        content()
    }

    var currentSelectedIndex by remember { mutableStateOf(selectedIndex) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(containerColor)
            .padding(bottom = 8.dp, top = 4.dp),
    ) {
        ABottomNavigationBarContent(
            items = scope.items,
            selectedIndex = currentSelectedIndex,
            onItemSelected = { currentSelectedIndex = it },
        )
    }
}
