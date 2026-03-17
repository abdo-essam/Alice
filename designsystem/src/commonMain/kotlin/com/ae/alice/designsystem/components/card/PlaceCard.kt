package com.ae.alice.designsystem.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import alice.designsystem.generated.resources.Res
import alice.designsystem.generated.resources.ic_bookmark_filled
import alice.designsystem.generated.resources.ic_bookmark_outlined
import alice.designsystem.generated.resources.places_details
import com.ae.alice.designsystem.components.icon.Icon
import com.ae.alice.designsystem.components.image.NetworkImage
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

private val CARD_HEIGHT = 130.dp
private val IMAGE_WIDTH = 110.dp

@Composable
fun PlaceCard(
    name: String,
    address: String,
    imageUrl: String,
    isSaved: Boolean,
    onDetailsClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val cardShape = RoundedCornerShape(Theme.radius.lg)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(CARD_HEIGHT)
            .shadow(
                elevation = 2.dp,
                shape = cardShape,
                ambientColor = Theme.colorScheme.shadeTertiary.copy(alpha = 0.06f),
                spotColor = Theme.colorScheme.shadeTertiary.copy(alpha = 0.08f),
            )
            .clip(cardShape)
            .background(Theme.colorScheme.background.surface)
            .padding(Theme.spacing._8)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            // ── Start side: Bookmark + Details button ──
            Column(
                modifier = Modifier
                    .height(CARD_HEIGHT - Theme.spacing._16) // account for parent padding
                    .padding(end = Theme.spacing._4),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
            ) {
                // Bookmark at top
                Icon(
                    painter = painterResource(
                        if (isSaved) Res.drawable.ic_bookmark_filled
                        else Res.drawable.ic_bookmark_outlined
                    ),
                    contentDescription = null,
                    tint = if (isSaved) Theme.colorScheme.brand.brand
                    else Theme.colorScheme.shadeSecondary,
                    modifier = Modifier
                        .size(22.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { onSaveClick() }
                )

                // Details button at bottom
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(Theme.radius.sm))
                        .background(Theme.colorScheme.brand.brand)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { onDetailsClick() }
                        .padding(
                            horizontal = Theme.spacing._20,
                            vertical = Theme.spacing._8
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(Res.string.places_details),
                        style = Theme.typography.label.medium,
                        color = Theme.colorScheme.brand.onBrand,
                    )
                }
            }

            // ── Middle: Title + Address (right-aligned, hugging image) ──
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = Theme.spacing._4),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Top,
            ) {
                Text(
                    text = name,
                    style = Theme.typography.title.small,
                    color = Theme.colorScheme.shadePrimary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(Theme.spacing._4))

                Text(
                    text = address,
                    style = Theme.typography.label.small,
                    color = Theme.colorScheme.shadeSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Spacer(modifier = Modifier.width(Theme.spacing._8))

            // ── End side: Image (fixed size) ──
            NetworkImage(
                url = imageUrl,
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(IMAGE_WIDTH)
                    .height(CARD_HEIGHT - Theme.spacing._16) // account for parent padding
                    .clip(RoundedCornerShape(Theme.radius.md))
            )
        }
    }
}