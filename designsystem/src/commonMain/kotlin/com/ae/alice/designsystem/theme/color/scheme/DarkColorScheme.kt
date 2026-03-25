package com.ae.alice.designsystem.theme.color.scheme

import com.ae.alice.designsystem.theme.color.White
import com.ae.alice.designsystem.theme.color.aliceColorPalette

/**
 * Dark-mode semantic mapping.
 * Maps raw [aliceColorPalette] shades → semantic ColorScheme roles.
 */
internal val DarkColorScheme = ColorScheme(
    brand = ColorScheme.Brand(
        brand = aliceColorPalette.copper.shade400,       // C4956A – unchanged in dark
        brandVariant = aliceColorPalette.copper.shade500, // B07A4F
        onBrand = White
    ),
    primary = ColorScheme.Primary(
        primary = White,
        onPrimary = aliceColorPalette.gray.shade900,      // 2A2520
        onPrimaryBody = aliceColorPalette.gray.shade700,   // 4A4540
        onPrimaryHint = aliceColorPalette.gray.shade600    // 7A7060
    ),
    secondary = ColorScheme.Secondary(
        secondary = aliceColorPalette.brown.shade500,     // 6A5C51
        secondaryText = aliceColorPalette.brown.shade200, // D0C0B0
        secondaryVariant = aliceColorPalette.brown.shade100 // E8E0D8
    ),
    border = ColorScheme.Border(
        disabled = aliceColorPalette.gray.shade700,       // 4A4540
        brand = aliceColorPalette.copper.shade400,
        error = aliceColorPalette.red.shade300,           // F97066
        success = aliceColorPalette.green.shade300        // 71CB87
    ),
    background = ColorScheme.Background(
        surfaceLow = aliceColorPalette.brown.shade900,    // 1A1512
        surface = aliceColorPalette.gray.shade900,        // 2A2520
        surfaceHigh = aliceColorPalette.gray.shade800,    // 3A3530
        bgError = aliceColorPalette.red.shade800,
        bgWarning = aliceColorPalette.yellow.shade800,
        bgSuccess = aliceColorPalette.green.shade800
    ),
    shadePrimary = White,
    shadeSecondary = aliceColorPalette.brown.shade200,    // D0C0B0
    shadeTertiary = aliceColorPalette.gray.shade600,      // 7A7060
    stroke = aliceColorPalette.gray.shade700,             // 4A4540
    textDisabled = aliceColorPalette.gray.shade600,
    disabled = aliceColorPalette.gray.shade600,
    error = aliceColorPalette.red.shade200,               // FDA29B
    warning = aliceColorPalette.yellow.shade300,          // FEC84B
    success = aliceColorPalette.green.shade300,           // 71CB87
    info = aliceColorPalette.blue.shade300,               // 64B5F6
    shimmerBase = aliceColorPalette.gray.shade800,        // 3A3530
    shimmerHighlight = aliceColorPalette.gray.shade700    // 4A4540
)
