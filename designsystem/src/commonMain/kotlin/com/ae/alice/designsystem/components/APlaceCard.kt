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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ae.alice.designsystem.theme.Theme

/**
 * A card for displaying a place/service provider — fully theme-aware.
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
        shape = RoundedCornerShape(Theme.radius.md),
        colors = CardDefaults.cardColors(
            containerColor = Theme.colorScheme.background.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Theme.spacing._12),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image / placeholder
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(Theme.radius.sm))
                    .background(Theme.colorScheme.background.surfaceHigh),
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
                        tint = Theme.colorScheme.textDisabled
                    )
                }
            }

            Spacer(modifier = Modifier.width(Theme.spacing._12))

            // Text + actions
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Theme.spacing._4)
            ) {
                Text(
                    text = name,
                    style = Theme.typography.title.small,
                    color = Theme.colorScheme.secondary.secondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = address,
                    style = Theme.typography.body.small,
                    color = Theme.colorScheme.shadeSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(Theme.spacing._4))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(Theme.spacing._8)
                ) {
                    OutlinedButton(
                        onClick = onDetailsClick,
                        modifier = Modifier.height(32.dp),
                        shape = RoundedCornerShape(Theme.radius.full),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Theme.colorScheme.brand.brand
                        )
                    ) {
                        Text(
                            text = "التفاصيل",
                            style = Theme.typography.label.medium
                        )
                    }

                    IconButton(
                        onClick = onSaveClick,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = if (isSaved) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                            contentDescription = null,
                            tint = if (isSaved) {
                                Theme.colorScheme.brand.brand
                            } else {
                                Theme.colorScheme.shadeSecondary
                            },
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}
