package com.ae.alice.designsystem.components.bottomsheet

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.unit.Velocity

fun sheetNestedScrollConnection(
    onPreScroll: (Float) -> Float = { 0f },
    onPostScroll: (Float) -> Float = { 0f },
    onPreFling: suspend (Float) -> Float = { 0f },
    onPostFling: suspend (Float) -> Unit = {},
): NestedScrollConnection = object : NestedScrollConnection {
    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        val consumed = onPreScroll(available.y)
        return Offset(0f, consumed)
    }

    override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
        val sheetConsumed = onPostScroll(available.y)
        return Offset(0f, sheetConsumed)
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        val consumed = onPreFling(available.y)
        return Velocity(0f, consumed)
    }

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
        onPostFling(available.y)
        return Velocity.Zero
    }
}
