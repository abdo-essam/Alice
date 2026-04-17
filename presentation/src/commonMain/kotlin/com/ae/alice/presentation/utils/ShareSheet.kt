package com.ae.alice.presentation.utils

import androidx.compose.runtime.Composable

@Composable
expect fun ShareSheet(title: String, message: String, shareLink: String, onDismiss: () -> Unit)
