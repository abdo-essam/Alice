package com.ae.alice.presentation.screens.auth.components

import alice.presentation.generated.resources.Res
import alice.presentation.generated.resources.ic_google
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.button.Button
import com.ae.alice.designsystem.theme.Theme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import com.ae.alice.designsystem.components.icon.Icon
import com.ae.alice.designsystem.components.text.Text
import org.jetbrains.compose.resources.painterResource

@Composable
fun GoogleSignInButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = Theme.colorScheme.shadePrimary,
        borderStroke = BorderStroke(1.dp, Theme.colorScheme.stroke),
        shape = RoundedCornerShape(Theme.radius.md),
        contentPadding = PaddingValues(vertical = 13.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(Res.drawable.ic_google),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Continue with Google",
                style = Theme.typography.title.small,
                color = Theme.colorScheme.shadePrimary
            )
        }
    }
}

