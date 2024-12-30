package com.example.presentation.ui.feature.admin.manageuser.mileage

import com.example.domain.model.User
import com.example.presentation.intent.UiEffect
import com.example.presentation.intent.UiEvent
import com.example.presentation.intent.UiState

class ChangeUserMileageContract {
    sealed class Event : UiEvent {
        data class OnClickSaveButton(val phoneNumber: String, val mileage: Int) : Event()
        data object OnClickMileageUp : Event()
        data object OnClickMileageDown : Event()
    }

    data class State(
        val user: User? = null
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowToast(val msg: String) : Effect()
        data class CompleteChangeMileage(val mileage: Int) : Effect()
    }
}
