package com.example.attendance.complete

import com.example.core_android.intent.UiEffect
import com.example.core_android.intent.UiEvent
import com.example.core_android.intent.UiState


class AttendanceCompleteContract {
    sealed class Event : UiEvent {}

    data class State(
        val name: String = "",
        val mileage: Int = 0
    ) : UiState

    sealed class Effect : UiEffect {}
}