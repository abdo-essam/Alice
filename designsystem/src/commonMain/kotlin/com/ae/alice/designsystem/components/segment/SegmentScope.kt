package com.ae.alice.designsystem.components.segment

import androidx.compose.runtime.Composable

/**
 * DSL scope for building [Segment] items.
 */
interface SegmentScope {
    fun item(
        title: String,
        content: @Composable SegmentScope.() -> Unit
    ) {
        error("SegmentScope.item is not implemented")
    }
}

internal class SegmentScopeImpl : SegmentScope {
    data class SegmentItem(
        val title: String,
        val content: @Composable SegmentScope.() -> Unit
    )

    val items = mutableListOf<SegmentItem>()

    override fun item(
        title: String,
        content: @Composable SegmentScope.() -> Unit
    ) {
        items.add(SegmentItem(title, content))
    }
}
