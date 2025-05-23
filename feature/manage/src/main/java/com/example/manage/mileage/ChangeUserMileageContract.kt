package com.example.manage.mileage

import com.example.base.UiEffect
import com.example.base.UiEvent
import com.example.base.UiState
import com.example.domain.model.User

class ChangeUserMileageContract {

    sealed class Event : UiEvent {
        data class OnClickSaveButton(val phoneNumber: String, val mileage: Int) : Event()
        data object OnClickMileageUp : Event()
        data object OnClickMileageDown : Event()
    }

    data class State(
        val isLoading: Boolean = false,
        val user: User? = null
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowToast(val msg: String) : Effect()
        data class CompleteChangeMileage(val mileage: Int) : Effect()
    }
}
