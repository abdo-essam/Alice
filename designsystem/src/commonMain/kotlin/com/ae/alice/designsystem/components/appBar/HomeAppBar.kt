package com.ae.alice.designsystem.components.appBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import alice.designsystem.generated.resources.Res
import alice.designsystem.generated.resources.alice_logo
import alice.designsystem.generated.resources.header_logo_desc
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

/**
 * Alice variant of MENA's HomeAppBar.
 * Shows the Alice logo, app title, and optional trailing content.
 */
@Composable
fun HomeAppBar(
    modifier: Modifier = Modifier,
    title: String = "ALICE",
    navigationIcon: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    titleColor: Color = Theme.colorScheme.shadePrimary,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 18.dp)
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(contentPadding)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            navigationIcon?.invoke()
            Text(
                text = title,
                color = titleColor,
                style = Theme.typography.title.medium
            )
        }
        trailingContent?.invoke()
    }
}
