package com.example.attendance.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.PilatesTheme
import com.example.feature.attendance.R

private val keyGap = 10.dp

@Composable
fun NumberKeypad(
    modifier: Modifier = Modifier,
    enterEnabled: Boolean,
    onNumberClick: (String) -> Unit,
    onBackspaceClick: () -> Unit,
    onEnterClick: () -> Unit
) {
    val numberRows = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(keyGap)
    ) {
        numberRows.forEach { rowNumbers ->
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(keyGap)
            ) {
                rowNumbers.forEach { number ->
                    NumberKey(
                        modifier = Modifier.weight(1f),
                        label = number,
                        onClick = { onNumberClick(number) },
                    )
                }
            }
        }

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(keyGap)
        ) {
            NumberKey(
                modifier = Modifier.weight(1f),
                label = stringResource(R.string.text_key_delete),
                style = KeyStyle.Secondary,
                onClick = onBackspaceClick,
            )
            NumberKey(
                modifier = Modifier.weight(1f),
                label = "0",
                onClick = { onNumberClick("0") },
            )
            NumberKey(
                modifier = Modifier.weight(1f),
                label = stringResource(R.string.text_key_enter),
                style = KeyStyle.Accent,
                enabled = enterEnabled,
                onClick = onEnterClick,
            )
        }
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun NumberKeypadPreview() {
    PilatesTheme {
        NumberKeypad(
            modifier = Modifier
                .width(420.dp)
                .height(400.dp),
            enterEnabled = true,
            onNumberClick = {},
            onBackspaceClick = {},
            onEnterClick = {}
        )
    }
}
