package com.example.attendance.complete.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.PilatesTheme

@Composable
fun CheckMark(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(72.dp)
            .background(PilatesTheme.colors.primary, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Filled.Check,
            contentDescription = null,
            tint = PilatesTheme.colors.onPrimary
        )
    }
}
