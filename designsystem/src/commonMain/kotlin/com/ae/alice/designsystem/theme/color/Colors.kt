package com.ae.alice.designsystem.theme.color

import androidx.compose.ui.graphics.Color
import com.ae.alice.designsystem.theme.color.palette.ColorPalette
import com.ae.alice.designsystem.theme.color.palette.ColorPalette.ColorScale

/**
 * Alice brand color palette — copper/bronze warm tones.
 *
 * Every raw color lives here. Semantic mapping (which shade means "primary",
 * "error", etc.) is done in [LightColorScheme] / [DarkColorScheme].
 */
val aliceColorPalette = ColorPalette(
    copper = ColorScale(
        shade50 = Color(0xFFFFF5EC),
        shade100 = Color(0xFFFFE8D4),
        shade200 = Color(0xFFE4C5A4),
        shade300 = Color(0xFFD4A574),
        shade400 = Color(0xFFC4956A),
        shade500 = Color(0xFFB07A4F),
        shade600 = Color(0xFF9A6A40),
        shade700 = Color(0xFF805530),
        shade800 = Color(0xFF664020),
        shade900 = Color(0xFF4D2E12)
    ),
    brown = ColorScale(
        shade50 = Color(0xFFF5F0EB),
        shade100 = Color(0xFFE8E0D8),
        shade200 = Color(0xFFD0C0B0),
        shade300 = Color(0xFF908070),
        shade400 = Color(0xFF7A6C61),
        shade500 = Color(0xFF6A5C51),
        shade600 = Color(0xFF4A3C31),
        shade700 = Color(0xFF3A2C21),
        shade800 = Color(0xFF302B26),
        shade900 = Color(0xFF1A1512)
    ),
    gray = ColorScale(
        shade50 = Color(0xFFFFFFFF),
        shade100 = Color(0xFFFAFAFA),
        shade200 = Color(0xFFF5F0EB),
        shade300 = Color(0xFFE8E0D8),
        shade400 = Color(0xFFB0A090),
        shade500 = Color(0xFF9E9090),
        shade600 = Color(0xFF7A7060),
        shade700 = Color(0xFF4A4540),
        shade800 = Color(0xFF3A3530),
        shade900 = Color(0xFF2A2520)
    ),
    red = ColorScale(
        shade50 = Color(0xFFFEE4E2),
        shade100 = Color(0xFFFECDCA),
        shade200 = Color(0xFFFDA29B),
        shade300 = Color(0xFFF97066),
        shade400 = Color(0xFFF04438),
        shade500 = Color(0xFFD92D20),
        shade600 = Color(0xFFB42318),
        shade700 = Color(0xFF912018),
        shade800 = Color(0xFF7A271A),
        shade900 = Color(0xFF55160C)
    ),
    yellow = ColorScale(
        shade50 = Color(0xFFFFFAEB),
        shade100 = Color(0xFFFEF0C7),
        shade200 = Color(0xFFFEDF89),
        shade300 = Color(0xFFFEC84B),
        shade400 = Color(0xFFFDB022),
        shade500 = Color(0xFFF79009),
        shade600 = Color(0xFFDC6803),
        shade700 = Color(0xFFB54708),
        shade800 = Color(0xFF93370D),
        shade900 = Color(0xFF7A2E0E)
    ),
    green = ColorScale(
        shade50 = Color(0xFFE6F6EA),
        shade100 = Color(0xFFC3E8CB),
        shade200 = Color(0xFF9BD9A9),
        shade300 = Color(0xFF71CB87),
        shade400 = Color(0xFF4CAF50),
        shade500 = Color(0xFF23B353),
        shade600 = Color(0xFF19A44A),
        shade700 = Color(0xFF06923E),
        shade800 = Color(0xFF008133),
        shade900 = Color(0xFF00621F)
    ),
    blue = ColorScale(
        shade50 = Color(0xFFE3F2FD),
        shade100 = Color(0xFFBBDEFB),
        shade200 = Color(0xFF90CAF9),
        shade300 = Color(0xFF64B5F6),
        shade400 = Color(0xFF42A5F5),
        shade500 = Color(0xFF2196F3),
        shade600 = Color(0xFF1E88E5),
        shade700 = Color(0xFF1976D2),
        shade800 = Color(0xFF1565C0),
        shade900 = Color(0xFF0D47A1)
    )
)

internal val White = Color(0xFFFFFFFF)
internal val White60 = White.copy(alpha = 0.6f)
internal val White38 = White.copy(alpha = 0.38f)
internal val Black = Color(0xFF000000)
