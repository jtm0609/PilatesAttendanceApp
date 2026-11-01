package com.example.designsystem.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = PilatesTypography(
    headlineLargeEB = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 28.sp),
    headlineLargeSB = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 28.sp),
    headlineLargeR = TextStyle(fontWeight = FontWeight.Normal, fontSize = 28.sp),
    headlineMediumB = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp),
    headlineMediumM = TextStyle(fontWeight = FontWeight.Medium, fontSize = 24.sp),
    headlineMediumR = TextStyle(fontWeight = FontWeight.Normal, fontSize = 24.sp),
    headlineSmallB = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
    headlineSmallM = TextStyle(fontWeight = FontWeight.Medium, fontSize = 20.sp),
    headlineSmallR = TextStyle(fontWeight = FontWeight.Normal, fontSize = 20.sp),

    titleLargeB = TextStyle(fontWeight = FontWeight.Bold, fontSize = 26.sp),
    titleLargeM = TextStyle(fontWeight = FontWeight.Medium, fontSize = 26.sp),
    titleLargeR = TextStyle(fontWeight = FontWeight.Normal, fontSize = 26.sp),
    titleMediumB = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp),
    titleMediumM = TextStyle(fontWeight = FontWeight.Medium, fontSize = 22.sp),
    titleMediumR = TextStyle(fontWeight = FontWeight.Normal, fontSize = 22.sp),
    titleSmallB = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
    titleSmallM = TextStyle(fontWeight = FontWeight.Medium, fontSize = 18.sp),
    titleSmallR = TextStyle(fontWeight = FontWeight.Normal, fontSize = 18.sp),

    labelLargeR = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 20.sp),
    labelMediumR = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 16.sp),
    labelSmallR = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 14.sp),

    bodyLargeR = TextStyle(fontWeight = FontWeight.Normal, fontSize = 18.sp, lineHeight = 28.sp),
    bodyMediumR = TextStyle(fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 24.sp),
    bodySmallR = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp),

    displayLargeR = TextStyle(fontWeight = FontWeight.Normal, fontSize = 72.sp),
    displayMediumR = TextStyle(fontWeight = FontWeight.Normal, fontSize = 48.sp),
    displaySmallR = TextStyle(fontWeight = FontWeight.Normal, fontSize = 32.sp),

    // Serif display: echoes the logotype. Reserved for a member's name.
    displaySerif = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Medium,
        fontSize = 84.sp,
        letterSpacing = (-1).sp
    ),
    keypad = TextStyle(fontWeight = FontWeight.Normal, fontSize = 40.sp),
    phoneDigits = TextStyle(fontWeight = FontWeight.Medium, fontSize = 36.sp, letterSpacing = 6.sp)
)

data class PilatesTypography(
    val headlineLargeEB: TextStyle,
    val headlineLargeSB: TextStyle,
    val headlineLargeR: TextStyle,
    val headlineMediumB: TextStyle,
    val headlineMediumM: TextStyle,
    val headlineMediumR: TextStyle,
    val headlineSmallB: TextStyle,
    val headlineSmallM: TextStyle,
    val headlineSmallR: TextStyle,

    val titleLargeB: TextStyle,
    val titleLargeM: TextStyle,
    val titleLargeR: TextStyle,
    val titleMediumB: TextStyle,
    val titleMediumM: TextStyle,
    val titleMediumR: TextStyle,
    val titleSmallB: TextStyle,
    val titleSmallM: TextStyle,
    val titleSmallR: TextStyle,

    val labelLargeR: TextStyle,
    val labelMediumR: TextStyle,
    val labelSmallR: TextStyle,

    val bodyLargeR: TextStyle,
    val bodyMediumR: TextStyle,
    val bodySmallR: TextStyle,

    val displayLargeR: TextStyle,
    val displayMediumR: TextStyle,
    val displaySmallR: TextStyle,

    val displaySerif: TextStyle,
    val keypad: TextStyle,
    val phoneDigits: TextStyle,
)
