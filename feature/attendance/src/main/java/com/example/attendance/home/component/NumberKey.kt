package com.example.attendance.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.PilatesTheme
import com.example.designsystem.theme.Typography

enum class KeyStyle { Digit, Secondary, Accent }

@Composable
fun NumberKey(
    label: String,
    modifier: Modifier = Modifier,
    style: KeyStyle = KeyStyle.Digit,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val container = when {
        !enabled -> PilatesTheme.colors.surface
        style == KeyStyle.Accent -> PilatesTheme.colors.primary
        style == KeyStyle.Secondary -> PilatesTheme.colors.surface
        else -> PilatesTheme.colors.surfaceRaised
    }
    val content = when {
        !enabled -> PilatesTheme.colors.textDisabled
        style == KeyStyle.Accent -> PilatesTheme.colors.onPrimary
        style == KeyStyle.Secondary -> PilatesTheme.colors.textSecondary
        else -> PilatesTheme.colors.textPrimary
    }
    val textStyle = if (style == KeyStyle.Digit) Typography.keypad else Typography.titleMediumM

    Surface(
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(18.dp),
        color = container,
        contentColor = content,
        enabled = enabled,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                style = textStyle
            )
        }
    }
}
