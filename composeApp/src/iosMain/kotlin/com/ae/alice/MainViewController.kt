package com.ae.alice

import androidx.compose.ui.window.ComposeUIViewController
import com.ae.alice.di.initKoin

/**
 * iOS view controller factory.
 */
fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { 
    App() 
}