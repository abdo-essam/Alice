package com.ae.alice.designsystem.components.segment

import androidx.compose.runtime.Composable

interface ASegmentScope {
    fun item(
        title: String,
        content: @Composable ASegmentScope.() -> Unit,
    ) {
        error("The method is not implemented")
    }
}

internal class ASegmentScopeImpl : ASegmentScope {
    data class SegmentItem(
        val title: String,
        val content: @Composable ASegmentScope.() -> Unit,
    )

    val items = mutableListOf<SegmentItem>()

    override fun item(
        title: String,
        content: @Composable ASegmentScope.() -> Unit,
    ) {
        items.add(SegmentItem(title, content))
    }
}
