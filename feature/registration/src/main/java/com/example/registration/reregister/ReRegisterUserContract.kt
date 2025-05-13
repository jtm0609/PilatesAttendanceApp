package com.example.registration.reregister

import com.example.base.UiEffect
import com.example.base.UiEvent
import com.example.base.UiState
import com.example.domain.model.User

class ReRegisterUserContract {
    sealed class Event : UiEvent {
        data class OnChangedDuration(val duration: String) : Event()
        data class OnCompleteSetDuration(val duration: String) : Event()
        data class OnCompleteStartDate(val year: Int, val month: Int, val day: Int) :
            Event()

        data class ClickedSave(
            val user: User
        ) : Event()

        data object OnClickSetDuration : Event()
        data object OnClickSetBeginDate : Event()
        data object OnDismissStartDate : Event()
        data object OnDismissDurationView : Event()
    }

    data class State(
        val isLoading: Boolean = false,
        val user: User? = null,
        val durationValues: List<String> = listOf("2주", "4주", "8주", "12주"),
        val isShowDurationView: Boolean = false,
        val isShowStartDateView: Boolean = false,
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowToast(val msg: String) : Effect()
        data class CompleteRegister(val user: User) : Effect()
    }
}