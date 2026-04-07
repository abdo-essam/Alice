package com.ae.alice.presentation.screens.cardetails.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.ic_arrow_left
import com.ae.alice.designsystem.components.appBar.AppBar
import com.ae.alice.designsystem.components.icon.Icon
import com.ae.alice.designsystem.theme.Theme
import org.jetbrains.compose.resources.painterResource
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import alice.presentation.generated.resources.ic_bookmark_filled
import alice.presentation.generated.resources.ic_bookmark_outlined

/**
 * MENA-style app bar for car details — back arrow + title.
 * Uses the design system's [AppBar] with [AppBarOptionContainer] leading content.
 */
@Composable
fun CarDetailsAppBar(
    title: String,
    isSaved: Boolean,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
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
        onLeadingClick = onBackClick,
        trailingContent = {
            Icon(
                painter = painterResource(
                    if (isSaved) Res.drawable.ic_bookmark_filled
                    else Res.drawable.ic_bookmark_outlined
                ),
                contentDescription = null,
                tint = if (isSaved) Theme.colorScheme.brand.brand
                else Theme.colorScheme.shadeSecondary,
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onSaveClick() }
            )
        }
    )
}
