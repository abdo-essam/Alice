package com.ae.alice.designsystem.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ae.alice.designsystem.theme.AColors

/**
 * App header component — clean brand title only.
 */
@Composable
fun AHeader(
    modifier: Modifier = Modifier,
    title: String = "ALICE"
) {
    Text(
        text = title,
        modifier = modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        color = AColors.Secondary,
        textAlign = TextAlign.Start,
        letterSpacing = 2.sp
    )
}
