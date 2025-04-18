package com.example.presentation.ui.feature.admin.manageuser.attendancecount

import com.example.presentation.intent.UiEffect
import com.example.presentation.intent.UiEvent
import com.example.presentation.intent.UiState

class ChangeAttendanceCountContract {
    sealed class Event : UiEvent {
        data class OnClickSaveButton(val count: Int) : Event()
        data object OnClickCountUp : Event()
        data object OnClickCountDown : Event()
    }

    data class State(
        val isLoading: Boolean = false,
        val name: String = "",
        val phone: String = "",
        val mileage: Int = 0,
        val attendanceCount: Int = 1
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowToast(val msg: String) : Effect()
        data object CompleteChangeMileage : Effect()
    }
}
