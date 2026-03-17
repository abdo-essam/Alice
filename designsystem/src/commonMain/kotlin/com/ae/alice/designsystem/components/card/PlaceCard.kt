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
import alice.designsystem.generated.resources.places_address_prefix
import alice.designsystem.generated.resources.places_details
import alice.designsystem.generated.resources.places_rating_buyers
import alice.designsystem.generated.resources.places_save
import com.ae.alice.designsystem.components.icon.Icon
import com.ae.alice.designsystem.components.text.Text
import com.ae.alice.designsystem.theme.Theme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

/**
 * Place card matching the reference design:
 *
 * ┌─────────────────────────────────────────────────────┐
 * │ 🔖          Place Name                    [IMAGE]   │
 * │             العنوان : full address text              │
 * │ [ تفاصيل ]           ★ 4.6  ( 255 مشتري )          │
 * └─────────────────────────────────────────────────────┘
 *
 * Features:
 * - Bookmark toggle with brand accent when saved
 * - Rounded image thumbnail on the trailing side
 * - Copper/brand "details" CTA button
 * - Gold star rating with review count
 * - Subtle card elevation
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
                elevation = 4.dp,
                shape = cardShape,
                ambientColor = Theme.colorScheme.shadeTertiary.copy(alpha = 0.06f),
                spotColor = Theme.colorScheme.shadeTertiary.copy(alpha = 0.08f),
            )
            .clip(cardShape)
            .background(Theme.colorScheme.background.surface)
            .padding(Theme.spacing._12)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
        ) {
            // ── Left: Bookmark + Text content ──
            Column(
                modifier = Modifier.weight(1f),
            ) {
                // Row 1: Bookmark icon + Place name
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(
                            if (isSaved) Res.drawable.ic_bookmark_filled
                            else Res.drawable.ic_bookmark_outlined
                        ),
                        contentDescription = stringResource(Res.string.places_save),
                        tint = if (isSaved) Theme.colorScheme.brand.brand
                        else Theme.colorScheme.shadeSecondary,
                        modifier = Modifier
                            .size(22.dp)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) { onSaveClick() }
                    )

                    Spacer(modifier = Modifier.width(Theme.spacing._8))

                    Text(
                        text = name,
                        style = Theme.typography.title.small,
                        color = Theme.colorScheme.shadePrimary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(Theme.spacing._4))

                // Row 2: Address
                Text(
                    text = "${stringResource(Res.string.places_address_prefix)} $address",
                    style = Theme.typography.label.small,
                    color = Theme.colorScheme.shadeSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 30.dp)
                )

                Spacer(modifier = Modifier.height(Theme.spacing._12))

                // Row 3: Details button + Rating
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    // Details CTA
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

                    // Rating badge
                    if (rating > 0) {
                        RatingBadge(
                            rating = rating,
                            reviewCount = reviewCount,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(Theme.spacing._12))

            // ── Right: Image thumbnail ──
            PlaceImageThumbnail(
                imageUrl = imageUrl,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(Theme.radius.md))
            )
        }
    }
}

/**
 * Rating: ★ 4.6  ( 255 مشتري )
 *
 * RTL-aware — star appears on the right in Arabic layout.
 */
@Composable
private fun RatingBadge(
    rating: Double,
    reviewCount: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
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

/**
 * Image placeholder — replace internals with your async image loader
 * (Coil / Kamel / Ktor Image) when real URLs are available.
 */
@Composable
private fun PlaceImageThumbnail(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(Theme.colorScheme.background.surfaceHigh),
        contentAlignment = Alignment.Center,
    ) {
        // TODO: Replace with async image loading
        // AsyncImage(
        //     model = imageUrl,
        //     contentDescription = null,
        //     contentScale = ContentScale.Crop,
        //     modifier = Modifier.matchParentSize()
        // )
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
