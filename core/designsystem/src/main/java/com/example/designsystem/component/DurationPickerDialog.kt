package com.example.designsystem.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.R
import com.example.designsystem.theme.PilatesTheme
import com.example.designsystem.theme.Typography

@Composable
fun <T> DurationPickerDialog(
    isShow: Boolean,
    durationState: T,
    durationList: List<T>,
    label: (T) -> String,
    onClickDurationEvent: (T) -> Unit,
    onDismissEvent: () -> Unit,
    onValueChange: (T) -> Unit
) {
    if (!isShow) return

    BackHandler { onDismissEvent() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.55f))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onDismissEvent
            ),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .width(480.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {}
                ),
            shape = RoundedCornerShape(24.dp),
            color = PilatesTheme.colors.surface,
        ) {
            Column(
                modifier = Modifier.padding(28.dp)
            ) {
                Text(
                    text = stringResource(R.string.msg_guide_selection_duration),
                    color = PilatesTheme.colors.textPrimary,
                    style = Typography.titleMediumM
                )

                DurationOptions(
                    modifier = Modifier.padding(top = 20.dp, bottom = 24.dp),
                    options = durationList,
                    selected = durationState,
                    label = label,
                    onSelect = onValueChange,
                )

                ConfirmButton(
                    text = stringResource(R.string.text_setting_button),
                    onClick = { onClickDurationEvent(durationState) }
                )
            }
        }
    }
}

@Composable
private fun <T> DurationOptions(
    options: List<T>,
    selected: T,
    label: (T) -> String,
    modifier: Modifier = Modifier,
    onSelect: (T) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        options.chunked(2).forEach { rowOptions ->
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                rowOptions.forEach { option ->
                    DurationOption(
                        modifier = Modifier.weight(1f),
                        label = label(option),
                        isSelected = option == selected,
                        onClick = { onSelect(option) },
                    )
                }
            }
        }
    }
}

@Composable
private fun DurationOption(
    label: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(14.dp)
    Surface(
        modifier = modifier.border(1.dp, if (isSelected) PilatesTheme.colors.primary else PilatesTheme.colors.outline, shape),
        shape = shape,
        color = if (isSelected) PilatesTheme.colors.primary else PilatesTheme.colors.surfaceRaised,
        contentColor = if (isSelected) PilatesTheme.colors.onPrimary else PilatesTheme.colors.textSecondary,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 18.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                style = Typography.titleMediumM.copy(
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium
                )
            )
        }
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun DurationPickerDialogPreview() {
    PilatesTheme {
        DurationPickerDialog(
            isShow = true,
            durationState = "4주",
            durationList = listOf("2주", "4주", "8주", "12주"),
            label = { it },
            onClickDurationEvent = {},
            onDismissEvent = {},
            onValueChange = {}
        )
    }
}
