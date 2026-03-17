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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import alice.designsystem.generated.resources.Res
import alice.designsystem.generated.resources.ic_bookmark_filled
import alice.designsystem.generated.resources.ic_bookmark_outlined
import alice.designsystem.generated.resources.ic_star
import alice.designsystem.generated.resources.places_details
import alice.designsystem.generated.resources.places_rating_buyers
import com.ae.alice.designsystem.components.icon.Icon
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

/**
 * Place card for Arabic RTL layout:
 *
 * ┌──────────────────────────────────────────────────────┐
 * │  🔖                                      [IMAGE]    │
 * │                              Place Name             │
 * │                              address text           │
 * │  [تفاصيل]        ★ 4.6  ( 255 مشتري )              │
 * └──────────────────────────────────────────────────────┘
 *
 * In RTL:
 * - Save bookmark: top-start (top-right visually)
 * - Image: end side (left visually)
 * - Title + address: after image
 * - Details button: bottom-start (bottom-right visually)
 * - Rating: bottom-end (bottom-left visually)
 */
@Composable
fun PlaceCard(
    name: String,
    address: String,
    imageUrl: String,
    isSaved: Boolean,
    rating: Double,
    reviewCount: Int,
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
            // ── Top section: Bookmark + Image + Text ──
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
            ) {
                // Bookmark icon (start side — right in RTL)
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

                // Spacer pushes content to the end
                Spacer(modifier = Modifier.weight(1f))

                // Title + Address column (before image in RTL = visually center-right)
                Column(
                    modifier = Modifier
                        .weight(3f)
                        .padding(horizontal = Theme.spacing._8),
                    horizontalAlignment = Alignment.End,
                ) {
                    Text(
                        text = name,
                        style = Theme.typography.title.small,
                        color = Theme.colorScheme.shadePrimary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = androidx.compose.ui.text.style.TextAlign.End
                    )

                    Spacer(modifier = Modifier.height(Theme.spacing._4))

                    Text(
                        text = address,
                        style = Theme.typography.label.small,
                        color = Theme.colorScheme.shadeSecondary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = androidx.compose.ui.text.style.TextAlign.End
                    )
                }

                // Image (end side — left in RTL, but we want it on the left/end)
                PlaceImageThumbnail(
                    imageUrl = imageUrl,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(Theme.radius.md))
                )
            }

            Spacer(modifier = Modifier.height(Theme.spacing._12))

            // ── Bottom section: Details button + Rating ──
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                // Details CTA button (start = right in RTL)
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

                // Rating badge (end = left in RTL)
                if (rating > 0) {
                    RatingBadge(
                        rating = rating,
                        reviewCount = reviewCount,
                    )
                }
            }
        }
    }
}

@Composable
private fun RatingBadge(
    rating: Double,
    reviewCount: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "( $reviewCount ${stringResource(Res.string.places_rating_buyers)} )",
            style = Theme.typography.label.small,
            color = Theme.colorScheme.shadeSecondary,
        )

        Spacer(modifier = Modifier.width(Theme.spacing._4))

        Text(
            text = formatRating(rating),
            style = Theme.typography.label.medium,
            color = Theme.colorScheme.shadePrimary,
        )

        Spacer(modifier = Modifier.width(Theme.spacing._2))

        Icon(
            painter = painterResource(Res.drawable.ic_star),
            contentDescription = null,
            tint = StarGold,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
private fun PlaceImageThumbnail(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.background(Theme.colorScheme.background.surfaceHigh),
        contentAlignment = Alignment.Center,
    ) {
        // TODO: Replace with AsyncImage(model = imageUrl, ...)
    }
}

private fun formatRating(rating: Double): String {
    return if (rating == rating.toLong().toDouble()) {
        rating.toLong().toString()
    } else {
        "%.1f".format(rating)
    }
}

private val StarGold = Color(0xFFFDB022)
