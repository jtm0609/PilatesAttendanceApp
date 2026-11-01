package com.example.attendance.complete.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.PilatesTheme

@Composable
fun AutoReturnBar(
    durationMillis: Int,
    modifier: Modifier = Modifier
) {
    var target by remember { mutableStateOf(1f) }
    val progress by animateFloatAsState(
        targetValue = target,
        animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing),
        label = "autoReturn"
    )
    LaunchedEffect(Unit) { target = 0f }

    LinearProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .height(4.dp),
        color = PilatesTheme.colors.primary,
        trackColor = PilatesTheme.colors.outline,
        progress = { progress }
    )
}
