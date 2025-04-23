package com.example.search

import com.example.core_android.intent.UiEffect
import com.example.core_android.intent.UiEvent
import com.example.core_android.intent.UiState
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