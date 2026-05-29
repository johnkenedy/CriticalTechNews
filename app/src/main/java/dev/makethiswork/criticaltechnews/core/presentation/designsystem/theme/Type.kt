package dev.makethiswork.criticaltechnews.core.presentation.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

val SerifFamily: FontFamily = FontFamily.Serif
val SansFamily: FontFamily = FontFamily.Default
val MonoFamily: FontFamily = FontFamily.Monospace

val KickerStyle = TextStyle(
    fontFamily = MonoFamily,
    fontSize = 10.5.sp,
    fontWeight = FontWeight.Medium,
    letterSpacing = 0.14.em,
)

val CriticalTechNewsTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = SerifFamily,
        fontSize = 27.sp,
        lineHeight = 30.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = (-0.02).em,
    ),
    headlineSmall = TextStyle(
        fontFamily = SerifFamily,
        fontSize = 23.sp,
        lineHeight = 27.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = (-0.015).em,
    ),
    titleMedium = TextStyle(
        fontFamily = SerifFamily,
        fontSize = 16.5.sp,
        lineHeight = 20.8.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = (-0.005).em,
    ),
    bodyLarge = TextStyle(
        fontFamily = SerifFamily,
        fontSize = 18.sp,
        lineHeight = 27.sp,
        fontWeight = FontWeight.Medium,
    ),
    bodyMedium = TextStyle(
        fontFamily = SerifFamily,
        fontSize = 16.sp,
        lineHeight = 25.9.sp,
        fontWeight = FontWeight.Normal,
    ),
    bodySmall = TextStyle(
        fontFamily = SansFamily,
        fontSize = 13.5.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Normal,
    ),
    labelMedium = TextStyle(
        fontFamily = SansFamily,
        fontSize = 11.5.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Normal,
    ),
    labelSmall = KickerStyle,
)
