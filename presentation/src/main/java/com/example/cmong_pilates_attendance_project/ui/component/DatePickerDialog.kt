package com.example.cmong_pilates_attendance_project.ui.component

import android.app.DatePickerDialog
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun DatePickerDialog(
    isShow: Boolean,
    startYear: Int,
    startMonth: Int,
    startDay: Int,
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

