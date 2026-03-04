package com.ae.alice.presentation.screens.cardetails.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.ic_arrow_left
import com.ae.alice.designsystem.components.appBar.AppBar
import com.ae.alice.designsystem.components.icon.Icon
import com.ae.alice.designsystem.theme.Theme
import org.jetbrains.compose.resources.painterResource

/**
 * MENA-style app bar for car details — back arrow + title.
 * Uses the design system's [AppBar] with [AppBarOptionContainer] leading content.
 */
@Composable
fun CarDetailsAppBar(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AppBar(
        title = title,
        modifier = modifier,
        leadingContent = {
            Icon(
                painter = painterResource(Res.drawable.ic_arrow_left),
                contentDescription = "Back",
                tint = Theme.colorScheme.primary.primary
            )
        },
        onLeadingClick = onBackClick
    )
}
