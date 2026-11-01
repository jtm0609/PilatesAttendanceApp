package com.example.designsystem.component

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.designsystem.R
import com.example.designsystem.theme.PilatesTheme
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.Calendar
import java.util.GregorianCalendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    isShow: Boolean,
    startYear: Int = GregorianCalendar().get(Calendar.YEAR),
    startMonth: Int = GregorianCalendar().get(Calendar.MONTH),
    startDay: Int = GregorianCalendar().get(Calendar.DATE),
    onDataSetEvent: (year: Int, month: Int, day: Int) -> Unit,
    onDismissEvent: () -> Unit,
) {
    if (!isShow) return

    val initialMillis = LocalDate.of(startYear, startMonth + 1, startDay)
        .atStartOfDay(ZoneOffset.UTC)
        .toInstant()
        .toEpochMilli()

    val state = rememberDatePickerState(initialSelectedDateMillis = initialMillis)

    val colors = DatePickerDefaults.colors(containerColor = PilatesTheme.colors.surface)

    DatePickerDialog(
        colors = colors,
        onDismissRequest = onDismissEvent,
        confirmButton = {
            TextButton(
                onClick = {
                    val millis = state.selectedDateMillis ?: initialMillis
                    val date = Instant.ofEpochMilli(millis).atZone(ZoneOffset.UTC).toLocalDate()
                    onDataSetEvent(date.year, date.monthValue - 1, date.dayOfMonth)
                }
            ) {
                Text(text = stringResource(R.string.text_confirm_button), color = PilatesTheme.colors.primary)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissEvent) {
                Text(text = stringResource(R.string.text_cancel_button), color = PilatesTheme.colors.textSecondary)
            }
        }
    ) {
        DatePicker(
            state = state,
            colors = colors,
            showModeToggle = false
        )
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun DatePickerDialogPreview() {
    ComponentPreview {
        DatePickerDialog(
            isShow = true,
            startYear = 2026,
            startMonth = 10,
            startDay = 1,
            onDataSetEvent = { _, _, _ -> },
            onDismissEvent = {}
        )
    }
}
