package com.example.cmong_pilates_attendance_project.ui.feature.attendance.complete

import com.example.cmong_pilates_attendance_project.intent.UiEffect
import com.example.cmong_pilates_attendance_project.intent.UiEvent
import com.example.cmong_pilates_attendance_project.intent.UiState
import java.util.Calendar
import java.util.GregorianCalendar

class AttendanceCompleteContract {
    sealed class Event : UiEvent {}

    data class State(
        //이용 기간 설정 뷰 Visible 상태
        val name: String = "",
        val mileage: Int = 0
    ) : UiState

    sealed class Effect : UiEffect {}
}