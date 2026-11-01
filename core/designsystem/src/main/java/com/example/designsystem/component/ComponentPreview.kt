package com.example.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.PilatesTheme

@Composable
internal fun ComponentPreview(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    PilatesTheme(darkTheme = darkTheme) {
        Box(
            modifier = Modifier
                .background(PilatesTheme.colors.background)
                .padding(24.dp)
        ) {
            content()
        }
    }
}
