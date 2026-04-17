package com.ae.alice.presentation.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.ic_invite_friends
import alice.presentation.generated.resources.profile_invite_friends_subtitle
import alice.presentation.generated.resources.profile_invite_friends_title
import com.ae.alice.designsystem.components.icon.Icon
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun InviteFriendsCard(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(top = Theme.spacing._24)
            .fillMaxWidth()
            .clip(RoundedCornerShape(Theme.radius.lg))
            .background(
                Theme.colorScheme.primary.primary.copy(alpha = 0.08f)
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            )
            .padding(Theme.spacing._8),
        horizontalArrangement = Arrangement.spacedBy(Theme.spacing._12),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .clip(RoundedCornerShape(Theme.radius.md))
                .background(Theme.colorScheme.background.surface)
                .padding(Theme.spacing._12)
                .size(Theme.spacing._24),
            painter = painterResource(Res.drawable.ic_invite_friends),
            tint = Theme.colorScheme.shadePrimary,
            contentDescription = null,
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(Theme.spacing._2)
        ) {
            Text(
                text = stringResource(Res.string.profile_invite_friends_title),
                style = Theme.typography.label.medium,
                color = Theme.colorScheme.shadePrimary,
                textAlign = TextAlign.Center,
            )
            Text(
                text = stringResource(Res.string.profile_invite_friends_subtitle),
                style = Theme.typography.label.small,
                color = Theme.colorScheme.shadeSecondary,
                textAlign = TextAlign.Center,
            )
        }
    }
}
