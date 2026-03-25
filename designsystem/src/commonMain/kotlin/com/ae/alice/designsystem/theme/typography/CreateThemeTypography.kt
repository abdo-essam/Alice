package com.ae.alice.designsystem.theme.typography

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import alice.designsystem.generated.resources.Res
import alice.designsystem.generated.resources.ibm_plex_sans_arabic_medium
import alice.designsystem.generated.resources.ibm_plex_sans_arabic_regular
import alice.designsystem.generated.resources.ibm_plex_sans_arabic_semi_bold
import alice.designsystem.generated.resources.poppins_medium
import alice.designsystem.generated.resources.poppins_regular
import alice.designsystem.generated.resources.poppins_semi_bold
import org.jetbrains.compose.resources.Font

/**
 * Builds a [Typography] instance using Poppins (English) or IBM Plex Sans Arabic
 * (Arabic), matching MENA's typography system exactly.
 */
@Composable
fun createThemeTypography(language: String = "en"): Typography {
    val poppinsFontFamily = FontFamily(
        Font(resource = Res.font.poppins_regular, FontWeight.Normal),
        Font(resource = Res.font.poppins_medium, FontWeight.Medium),
        Font(resource = Res.font.poppins_semi_bold, FontWeight.SemiBold),
    )
    val ibmPlexArabicFontFamily = FontFamily(
        Font(resource = Res.font.ibm_plex_sans_arabic_regular, FontWeight.Normal),
        Font(resource = Res.font.ibm_plex_sans_arabic_medium, FontWeight.Medium),
        Font(resource = Res.font.ibm_plex_sans_arabic_semi_bold, FontWeight.SemiBold),
    )
    val fontFamily = when (language) {
        "ar" -> ibmPlexArabicFontFamily
        else -> poppinsFontFamily
    }

    return Typography(
        headline = Typography.Headline(
            large = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
                lineHeight = 42.sp
            ),
            medium = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                lineHeight = 36.sp
            ),
            small = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 24.sp
            )
        ),
        title = Typography.Title(
            large = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                lineHeight = 30.sp
            ),
            medium = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                lineHeight = 28.sp
            ),
            small = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 24.sp
            )
        ),
        body = Typography.Body(
            large = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 28.sp
            ),
            medium = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp
            ),
            small = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 22.sp
            )
        ),
        label = Typography.Label(
            large = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 24.sp
            ),
            medium = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 22.sp
            ),
            small = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp,
                lineHeight = 16.sp
            )
        )
    )
}
