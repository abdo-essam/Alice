package com.ae.alice.designsystem.components

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.ImageNotSupported
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.ae.alice.designsystem.theme.AColors
import com.ae.alice.designsystem.theme.ADimensions

/**
 * A card component for displaying a place/service provider.
 * Shows image, place name, address, details button, and a save/bookmark toggle.
 */
@Composable
fun APlaceCard(
    name: String,
    address: String,
    imageUrl: String?,
    isSaved: Boolean,
    onDetailsClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(ADimensions.RadiusMd),
        colors = CardDefaults.cardColors(
            containerColor = AColors.Light.Surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = ADimensions.CardElevation
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ADimensions.SpacingMd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image / placeholder
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(ADimensions.RadiusSm))
                    .background(AColors.Light.SurfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                if (!imageUrl.isNullOrBlank()) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = name,
                        modifier = Modifier.size(80.dp),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.ImageNotSupported,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = AColors.Light.TextDisabled
                    )
                }
            }

            Spacer(modifier = Modifier.width(ADimensions.SpacingMd))

            // Text + actions
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(ADimensions.SpacingXs)
            ) {
                Text(
                    text = name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AColors.Secondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = address,
                    fontSize = 12.sp,
                    color = AColors.Light.TextSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(ADimensions.SpacingXs))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(ADimensions.SpacingSm)
                ) {
                    OutlinedButton(
                        onClick = onDetailsClick,
                        modifier = Modifier.height(32.dp),
                        shape = RoundedCornerShape(ADimensions.RadiusFull),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = AColors.Primary
                        )
                    ) {
                        Text(
                            text = "التفاصيل",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    IconButton(
                        onClick = onSaveClick,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = if (isSaved) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                            contentDescription = null,
                            tint = if (isSaved) AColors.Primary else AColors.Light.TextSecondary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}
