package com.example.attendance.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
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

@Preview(device = Devices.TABLET)
@Composable
private fun NumberKeyPreview() {
    PilatesTheme {
        Row(
            modifier = Modifier
                .width(480.dp)
                .height(90.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            NumberKey(modifier = Modifier.weight(1f), label = "7", onClick = {})
            NumberKey(modifier = Modifier.weight(1f), label = "지우기", style = KeyStyle.Secondary, onClick = {})
            NumberKey(modifier = Modifier.weight(1f), label = "입장", style = KeyStyle.Accent, onClick = {})
            NumberKey(modifier = Modifier.weight(1f), label = "입장", style = KeyStyle.Accent, enabled = false, onClick = {})
        }
    }
}
