package com.example.search

import com.example.base.UiEffect
import com.example.base.UiEvent
import com.example.base.UiState
import com.example.domain.model.User

class SearchUserContract {

    sealed class Event : UiEvent {
        data class OnClickNextButton(val phoneNumber: String) : Event()
        data class OnChangePhoneNumber(val phoneNumber: String) : Event()
    }

    data class State(
        val isLoading : Boolean = false,
        val phone: String = "",
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowToast(val msg: String) : Effect()
        data class CompleteSearch(val user: User) : Effect()
    }
}