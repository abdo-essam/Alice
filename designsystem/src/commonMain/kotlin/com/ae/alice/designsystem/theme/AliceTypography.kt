package com.ae.alice.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import alice.designsystem.generated.resources.Res
import alice.designsystem.generated.resources.poppins_regular
import alice.designsystem.generated.resources.poppins_medium
import alice.designsystem.generated.resources.poppins_semi_bold
import alice.designsystem.generated.resources.madimi_one_regular
import org.jetbrains.compose.resources.Font

/**
 * MENA-style grouped typography loaded from Poppins font resources.
 */
@Immutable
data class AliceTypography(
    val headline: Headline,
    val title: Title,
    val body: Body,
    val label: Label,
    val appName: TextStyle
) {
    data class Headline(
        val large: TextStyle,
        val medium: TextStyle,
        val small: TextStyle
    )

    data class Title(
        val large: TextStyle,
        val medium: TextStyle,
        val small: TextStyle
    )

    data class Body(
        val large: TextStyle,
        val medium: TextStyle,
        val small: TextStyle
    )

    data class Label(
        val large: TextStyle,
        val medium: TextStyle,
        val small: TextStyle,
        val extraSmall: TextStyle
    )
}

@Composable
fun createAliceTypography(brandColor: androidx.compose.ui.graphics.Color): AliceTypography {
    val poppins = FontFamily(
        Font(resource = Res.font.poppins_regular, FontWeight.Normal),
        Font(resource = Res.font.poppins_medium, FontWeight.Medium),
        Font(resource = Res.font.poppins_semi_bold, FontWeight.SemiBold),
    )
    val madimi = FontFamily(
        Font(resource = Res.font.madimi_one_regular, FontWeight.Normal)
    )

    return AliceTypography(
        appName = TextStyle(
            fontSize = 28.sp,
            fontFamily = madimi,
            color = brandColor
        ),
        headline = AliceTypography.Headline(
            large = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
                lineHeight = 42.sp
            ),
            medium = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                lineHeight = 36.sp
            ),
            small = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 24.sp
            )
        ),
        title = AliceTypography.Title(
            large = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                lineHeight = 30.sp
            ),
            medium = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                lineHeight = 28.sp
            ),
            small = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 24.sp
            )
        ),
        body = AliceTypography.Body(
            large = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 28.sp
            ),
            medium = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp
            ),
            small = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 22.sp
            )
        ),
        label = AliceTypography.Label(
            large = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 24.sp
            ),
            medium = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 22.sp
            ),
            small = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp,
                lineHeight = 16.sp
            ),
            extraSmall = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                lineHeight = 16.sp
            )
        )
    )
}

internal val LocalAliceTypography =
    staticCompositionLocalOf<AliceTypography> { error("No AliceTypography provided") }
