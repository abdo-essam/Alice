package com.ae.alice.designsystem.components.segment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.theme.Theme
import kotlinx.coroutines.launch

/**
 * Tabbed segment control with swiping support via [HorizontalPager].
 * Matches MENA's Segment exactly — non-composable DSL for items,
 * uses pagerState.animateScrollToPage for smooth transitions.
 *
 * Usage:
 * ```
 * Segment {
 *     item("Tab 1") { /* content */ }
 *     item("Tab 2") { /* content */ }
 * }
 * ```
 */
@Composable
fun Segment(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(top = 16.dp),
    content: @Composable SegmentScope.() -> Unit
) {
    val scope = remember { SegmentScopeImpl() }.apply {
        items.clear()
        content()
    }
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { scope.items.size }
    )
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(Theme.radius.md))
                .background(Theme.colorScheme.background.surfaceHigh)
        ) {
            scope.items.forEachIndexed { index, item ->
                SegmentButton(
                    option = item.title,
                    isSelected = index == pagerState.currentPage,
                    onSelectChange = {
                        coroutineScope.launch { pagerState.animateScrollToPage(index) }
                    }
                )
            }
        }

        HorizontalPager(
            modifier = modifier.padding(contentPadding),
            state = pagerState
        ) { page ->
            scope.items.getOrNull(page)?.content(scope)
        }
    }
}
