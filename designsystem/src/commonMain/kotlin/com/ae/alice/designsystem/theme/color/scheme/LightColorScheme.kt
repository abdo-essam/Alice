package com.ae.alice.designsystem.theme.color.scheme

import com.ae.alice.designsystem.theme.color.White
import com.ae.alice.designsystem.theme.color.White38
import com.ae.alice.designsystem.theme.color.White60
import com.ae.alice.designsystem.theme.color.aliceColorPalette

/**
 * Light-mode semantic mapping.
 * Maps raw [aliceColorPalette] shades → semantic ColorScheme roles.
 */
internal val LightColorScheme = ColorScheme(
    brand = ColorScheme.Brand(
        brand = aliceColorPalette.copper.shade400,      // C4956A – primary copper
        brandVariant = aliceColorPalette.copper.shade300, // D4A574
        onBrand = White
    ),
    primary = ColorScheme.Primary(
        primary = aliceColorPalette.brown.shade600,       // 4A3C31 – dark brown text
        onPrimary = White,
        onPrimaryBody = White60,
        onPrimaryHint = White38
    ),
    secondary = ColorScheme.Secondary(
        secondary = aliceColorPalette.brown.shade600,     // 4A3C31
        secondaryText = aliceColorPalette.brown.shade400, // 7A6C61
        secondaryVariant = aliceColorPalette.brown.shade200 // D0C0B0
    ),
    border = ColorScheme.Border(
        disabled = aliceColorPalette.gray.shade300,       // E8E0D8
        brand = aliceColorPalette.copper.shade400,        // C4956A
        error = aliceColorPalette.red.shade600,           // B42318
        success = aliceColorPalette.green.shade700        // 06923E
    ),
    background = ColorScheme.Background(
        surfaceLow = aliceColorPalette.gray.shade100,     // FAFAFA
        surface = aliceColorPalette.gray.shade50,         // FFFFFF
        surfaceHigh = aliceColorPalette.gray.shade200,    // F5F0EB
        bgError = aliceColorPalette.red.shade50,          // FEE4E2
        bgWarning = aliceColorPalette.yellow.shade50,     // FFFAEB
        bgSuccess = aliceColorPalette.green.shade50       // E6F6EA
    ),
    shadePrimary = aliceColorPalette.brown.shade600,      // 4A3C31 – main text
    shadeSecondary = aliceColorPalette.brown.shade400,    // 7A6C61 – secondary text
    shadeTertiary = aliceColorPalette.gray.shade500,      // 9E9090 – hint text
    stroke = aliceColorPalette.gray.shade300,             // E8E0D8
    textDisabled = aliceColorPalette.gray.shade400,       // B0A090
    disabled = aliceColorPalette.gray.shade400,
    error = aliceColorPalette.red.shade400,               // F04438
    warning = aliceColorPalette.yellow.shade500,          // F79009
    success = aliceColorPalette.green.shade400,           // 4CAF50
    info = aliceColorPalette.blue.shade500,               // 2196F3
    shimmerBase = aliceColorPalette.gray.shade300,        // E8E0D8
    shimmerHighlight = aliceColorPalette.gray.shade200    // F5F0EB
)
