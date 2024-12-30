package com.example.presentation.ui.feature.attendance.complete

import com.example.presentation.intent.UiEffect
import com.example.presentation.intent.UiEvent
import com.example.presentation.intent.UiState

class AttendanceCompleteContract {
    sealed class Event : UiEvent {}

    data class State(
        val name: String = "",
        val mileage: Int = 0
    ) : UiState

    sealed class Effect : UiEffect {}
}