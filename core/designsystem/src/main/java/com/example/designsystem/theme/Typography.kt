package com.example.designsystem.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * 애플리케이션 디자인 시스템 타이포그래피 정의
 */
val Typography = PilatesTypography(
    headlineLargeEB = TextStyle(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 30.sp
    ),
    headlineLargeSB = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 30.sp
    ),
    headlineLargeR = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    headlineMediumB = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp
    ),
    headlineMediumM = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 25.sp
    ),
    headlineMediumR = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 25.sp
    ),
    headlineSmallB = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    headlineSmallM = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    headlineSmallR = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),

    titleLargeB = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    titleLargeM = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 30.sp
    ),
    titleLargeR = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    titleMediumB = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp
    ),
    titleMediumM = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 25.sp
    ),
    titleMediumR = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 25.sp
    ),
    titleSmallB =  TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    titleSmallM = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    titleSmallR = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),

    labelLargeR = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 30.sp
    ),
    labelMediumR = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    labelSmallR = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    bodyLargeR = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    bodyMediumR = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodySmallR = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 14.sp
    ),
    displayLargeR = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 90.sp
    ),
    displayMediumR = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 60.sp
    ),
    displaySmallR = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    )
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
)