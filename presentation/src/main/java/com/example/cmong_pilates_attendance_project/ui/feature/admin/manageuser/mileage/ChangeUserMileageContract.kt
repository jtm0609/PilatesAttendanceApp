package com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.mileage

import com.example.cmong_pilates_attendance_project.intent.UiEffect
import com.example.cmong_pilates_attendance_project.intent.UiEvent
import com.example.cmong_pilates_attendance_project.intent.UiState

class ChangeUserMileageContract {
    sealed class Event : UiEvent {
        data class OnClickSaveButton(val phoneNumber: String, val mileage: Int) : Event()
        data object OnClickMileageUp : Event()
        data object OnClickMileageDown : Event()
    }

    data class State(
        //이용 기간 설정 뷰 Visible 상태
        val name: String = "",
        val phone: String = "",
        val mileage: Int = 0
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowToast(val msg: String) : Effect()
        data class CompleteChangeMileage(val mileage: Int) : Effect()
    }
}
