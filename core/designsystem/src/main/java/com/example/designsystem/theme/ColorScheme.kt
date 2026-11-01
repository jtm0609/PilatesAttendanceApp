package com.example.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class PilatesColorScheme(
    val background: Color,
    val surface: Color,
    val surfaceRaised: Color,
    val outline: Color,
    val primary: Color,
    val onPrimary: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textDisabled: Color,
    val error: Color
)

internal val PilatesDarkColorScheme = PilatesColorScheme(
    background = Charcoal90,
    surface = Charcoal80,
    surfaceRaised = Charcoal70,
    outline = Charcoal60,
    primary = Sage30,
    onPrimary = Sage90,
    textPrimary = Ivory,
    textSecondary = Gray40,
    textDisabled = Gray60,
    error = Coral40
)

internal val PilatesLightColorScheme = PilatesColorScheme(
    background = Ivory,
    surface = White,
    surfaceRaised = Gray20,
    outline = Gray30,
    primary = Sage50,
    onPrimary = White,
    textPrimary = Charcoal90,
    textSecondary = Gray70,
    textDisabled = Gray40,
    error = Coral70
)

internal val LocalPilatesColorScheme = staticCompositionLocalOf { PilatesDarkColorScheme }
