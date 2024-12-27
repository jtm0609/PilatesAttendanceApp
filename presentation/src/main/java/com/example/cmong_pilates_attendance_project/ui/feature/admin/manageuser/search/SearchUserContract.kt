package com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.search

import com.example.cmong_pilates_attendance_project.intent.UiEffect
import com.example.cmong_pilates_attendance_project.intent.UiEvent
import com.example.cmong_pilates_attendance_project.intent.UiState
import com.example.data.model.UserEntity

class SearchUserContract {
    sealed class Event : UiEvent {
        data class OnClickNextButton(val phoneNumber: String) : Event()
        data class OnChangePhoneNumber(val phoneNumber: String) : Event()
    }

    data class State(
        //이용 기간 설정 뷰 Visible 상태
        val phone: String = "",
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowToast(val msg: String) : Effect()
        data class CompleteSearch(val user: UserEntity) : Effect()
    }
}