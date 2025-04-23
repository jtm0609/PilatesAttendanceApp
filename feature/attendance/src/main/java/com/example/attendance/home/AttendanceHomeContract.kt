package com.example.attendance.home

import com.example.core_android.intent.UiEffect
import com.example.core_android.intent.UiEvent
import com.example.core_android.intent.UiState
import com.example.domain.model.Admin
import com.example.domain.model.User

class AttendanceHomeContract {
    sealed class Event : UiEvent {
        data class OnClickNumber(val number: String) : Event()
        data object OnClickDelete : Event()
        data class OnClickOK(val phoneNumber: String) : Event()
        data object OnClickAdminMenu : Event()
    }

    data class State(
        val isLoading: Boolean = false,
        val phoneNumber: String = "",
        val searchedUser: User? = null, // 조회한 유저
        val adminData: Admin? = null
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowToast(val msg: String) : Effect()
        data object GoAdminPage : Effect()
        data class SuccessAttendance(val user: User) : Effect()
    }
}