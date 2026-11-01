package com.example.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object PilatesTheme {
    val colors: PilatesColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalPilatesColorScheme.current
}

@Composable
fun PilatesTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) PilatesDarkColorScheme else PilatesLightColorScheme

    CompositionLocalProvider(LocalPilatesColorScheme provides colors) {
        MaterialTheme(
            colorScheme = colors.toMaterialColorScheme(darkTheme),
            content = content
        )
    }
}

private fun PilatesColorScheme.toMaterialColorScheme(darkTheme: Boolean) =
    if (darkTheme) {
        darkColorScheme(
            primary = primary,
            secondary = textSecondary,
            background = background,
            surface = surface,
            surfaceVariant = surfaceRaised,
            outline = outline,
            error = error,
            onPrimary = onPrimary,
            onSecondary = background,
            onBackground = textPrimary,
            onSurface = textPrimary,
            onSurfaceVariant = textSecondary,
            onError = background
        )
    } else {
        lightColorScheme(
            primary = primary,
            secondary = textSecondary,
            background = background,
            surface = surface,
            surfaceVariant = surfaceRaised,
            outline = outline,
            error = error,
            onPrimary = onPrimary,
            onSecondary = background,
            onBackground = textPrimary,
            onSurface = textPrimary,
            onSurfaceVariant = textSecondary,
            onError = background
        )
    }
