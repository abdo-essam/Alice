package com.ae.alice.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ae.alice.designsystem.theme.AColors
import alice.designsystem.generated.resources.Res
import alice.designsystem.generated.resources.alice_logo
import org.jetbrains.compose.resources.painterResource

/**
 * App header component with hamburger menu icon and Alice logo.
 */
@Composable
fun AHeader(
    modifier: Modifier = Modifier,
    title: String = "ALICE",
    showMenuIcon: Boolean = false,
    onMenuClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showMenuIcon) {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = AColors.Secondary,
                    modifier = Modifier.size(28.dp)
                )
            }
        } else {
            Spacer(modifier = Modifier.width(12.dp))
        }

        // Alice logo
        Image(
            painter = painterResource(Res.drawable.alice_logo),
            contentDescription = "Alice Logo",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = AColors.Secondary,
            letterSpacing = 2.sp
        )
    }
}
