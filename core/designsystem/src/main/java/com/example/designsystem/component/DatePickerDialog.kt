package com.example.designsystem.component

import android.app.DatePickerDialog
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar
import java.util.GregorianCalendar

@Composable
fun DatePickerDialog(
    isShow: Boolean,
    startYear: Int = GregorianCalendar().get(Calendar.YEAR),
    startMonth: Int = GregorianCalendar().get(Calendar.MONTH),
    startDay: Int = GregorianCalendar().get(Calendar.DATE),
    onDataSetEvent: (year: Int, month: Int, day: Int) -> Unit,
    onDismissEvent: () -> Unit,
) {
    if (isShow) {
        BackHandler {
            onDismissEvent()
        }
        val dlg = DatePickerDialog(
            LocalContext.current,
            { pView, pYear, pMonth, pDayOfMonth ->
                onDataSetEvent(pYear, pMonth, pDayOfMonth)
            },
            startYear, startMonth, startDay
        )
        dlg.setOnCancelListener { onDismissEvent()}

        dlg.show()
    }
}

