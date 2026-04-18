package com.ae.alice.presentation.utils

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun ShareSheet(title: String, message: String, shareLink: String, onDismiss: () -> Unit) {
    val context = LocalContext.current

    LaunchedEffect(message, title) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "$message\n\n$shareLink")
            putExtra(Intent.EXTRA_TITLE, title)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(shareIntent)
        onDismiss()
    }
}
