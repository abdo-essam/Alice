package com.ae.alice.designsystem.components.scaffold

import androidx.compose.runtime.Composable

interface AScaffoldScope {
    fun bottomSheet(
        isVisible: Boolean,
        content: @Composable AScaffoldScope.(Boolean) -> Unit,
    ) {
        error("The method is not implemented")
    }

    fun dialog(
        isVisible: Boolean,
        content: @Composable AScaffoldScope.(Boolean) -> Unit,
    ) {
        error("The method is not implemented")
    }
}

internal class AScaffoldScopeImpl : AScaffoldScope {
    data class OverlayItem(
        val isVisible: Boolean,
        val content: @Composable AScaffoldScope.(Boolean) -> Unit,
    )

    val items = mutableListOf<OverlayItem>()

    override fun bottomSheet(
        isVisible: Boolean,
        content: @Composable AScaffoldScope.(Boolean) -> Unit,
    ) {
        items.add(OverlayItem(isVisible, content))
    }

    override fun dialog(
        isVisible: Boolean,
        content: @Composable AScaffoldScope.(Boolean) -> Unit,
    ) {
        items.add(OverlayItem(isVisible, content))
    }
}
