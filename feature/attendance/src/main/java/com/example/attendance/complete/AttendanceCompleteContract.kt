package com.example.attendance.complete

import com.example.base.UiEffect
import com.example.base.UiEvent
import com.example.base.UiState


class AttendanceCompleteContract {

    sealed class Event : UiEvent

    data class State(
        val name: String = "",
        val mileage: Int = 0
    ) : UiState

    sealed class Effect : UiEffect
}