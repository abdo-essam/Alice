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

/**
 * Place card — Arabic RTL layout:
 *
 * ┌──────────────────────────────────────────────┐
 * │  🔖                          Title  [IMAGE]  │
 * │                           Address..          │
 * │  [تفاصيل]                                    │
 * └──────────────────────────────────────────────┘
 *
 * RTL behavior:
 * - Bookmark: top-start (visually top-right)
 * - Image: end (visually left side... NO — end = left in LTR, right in RTL)
 *
 * Actually in RTL with Row:
 * - start = right side visually
 * - end = left side visually
 *
 * So: Bookmark (start=right) | spacer | Title+Address | Image (end=left)
 * But the design image shows: Bookmark top-left, Image top-right, Title center
 *
 * For the reference image (Arabic):
 * - Image is on the RIGHT (end in RTL = left... )
 *
 * Let's just use explicit ordering that works for RTL:
 * Row: [Bookmark] [Title+Address weight] [Image]
 * In RTL this renders as: Image | Title | Bookmark (right to left)
 * Which means: Image on LEFT, Title in CENTER, Bookmark on RIGHT ✓
 */
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
            .shadow(
                elevation = 2.dp,
                shape = cardShape,
                ambientColor = Theme.colorScheme.shadeTertiary.copy(alpha = 0.06f),
                spotColor = Theme.colorScheme.shadeTertiary.copy(alpha = 0.08f),
            )
            .clip(cardShape)
            .background(Theme.colorScheme.background.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Theme.spacing._12)
        ) {
            // ── Top row: Bookmark + Title/Address + Image ──
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
            ) {
                // Bookmark (start side)
                Icon(
                    painter = painterResource(
                        if (isSaved) Res.drawable.ic_bookmark_filled
                        else Res.drawable.ic_bookmark_outlined
                    ),
                    contentDescription = null,
                    tint = if (isSaved) Theme.colorScheme.brand.brand
                    else Theme.colorScheme.shadeSecondary,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { onSaveClick() }
                )

                Spacer(modifier = Modifier.width(Theme.spacing._4))

                // Title + Address (fills middle)
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = Theme.spacing._4),
                ) {
                    Text(
                        text = name,
                        style = Theme.typography.title.small,
                        color = Theme.colorScheme.shadePrimary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Spacer(modifier = Modifier.height(Theme.spacing._4))

                    Text(
                        text = address,
                        style = Theme.typography.label.small,
                        color = Theme.colorScheme.shadeSecondary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                Spacer(modifier = Modifier.width(Theme.spacing._8))

                // Image (end side)
                NetworkImage(
                    url = imageUrl,
                    contentDescription = name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(Theme.radius.md))
                )
            }

            Spacer(modifier = Modifier.height(Theme.spacing._12))

            // ── Bottom row: Details button only ──
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(Theme.radius.sm))
                    .background(Theme.colorScheme.brand.brand)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onDetailsClick() }
                    .padding(
                        horizontal = Theme.spacing._24,
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
    }
}
