package com.example.cmong_pilates_attendance_project.ui.feature.attendance.main

import com.example.cmong_pilates_attendance_project.intent.UiEffect
import com.example.cmong_pilates_attendance_project.intent.UiEvent
import com.example.cmong_pilates_attendance_project.intent.UiState
import com.example.data.model.AdminEntity
import com.example.data.model.UserEntity

class AttendanceMainContract {
    sealed class Event : UiEvent {
        data class OnClickNumber(val number: String) : Event()
        data object OnClickDelete : Event()
        data class OnClickOK(val phoneNumber: String) : Event()
        data object OnClickAdminMenu : Event()
    }

    data class State(
        //이용 기간 설정 뷰 Visible 상태
        val phoneNumber: String = "",
        val searchedUser: UserEntity? = null, // 조회한 유저
        val adminData: AdminEntity? = null
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowToast(val msg: String) : Effect()
        data object GoAdminPage : Effect()
        data class SuccessAttendance(val user: UserEntity) : Effect()
    }
}