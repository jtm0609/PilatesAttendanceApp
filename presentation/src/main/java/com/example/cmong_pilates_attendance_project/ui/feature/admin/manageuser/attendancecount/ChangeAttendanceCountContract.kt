package com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.attendancecount

import com.example.cmong_pilates_attendance_project.intent.UiEffect
import com.example.cmong_pilates_attendance_project.intent.UiEvent
import com.example.cmong_pilates_attendance_project.intent.UiState

class ChangeAttendanceCountContract {
    sealed class Event : UiEvent {
        data class OnClickSaveButton(val count: Int) : Event()
        data object OnClickCountUp : Event()
        data object OnClickCountDown : Event()
    }

    data class State(
        //이용 기간 설정 뷰 Visible 상태
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
