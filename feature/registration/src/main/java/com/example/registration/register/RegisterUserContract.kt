package com.example.registration.register

import com.example.base.UiEffect
import com.example.base.UiEvent
import com.example.base.UiState
import java.util.Calendar
import java.util.GregorianCalendar

class RegisterUserContract {

    sealed class Event : UiEvent {
        data class OnChangedName(val name: String) : Event()
        data class OnChangePhoneNumber(val phone: String) : Event()
        data class OnChangeDuration(val duration: String) : Event()
        data class OnCompleteSetDuration(val duration: String) : Event()
        data class OnCompleteStartDate(val year: Int, val month: Int, val day: Int) :
            Event()
        data class ClickedSave(
            val name: String,
            val phone: String,
            val durationText: String,
            val startDateText: String
        ) : Event()

        data object OnClickSetDuration : Event()
        data object OnClickSetBeginDate : Event()
        data object OnDismissStartDate : Event()
        data object OnDismissDurationView : Event()
    }

    data class State(
        val isLoading: Boolean = false,
        val name: String = "",
        val phone: String = "",
        val durationValues: List<String> = listOf("2주", "4주", "8주", "12주"),
        val durationState: String = durationValues[0],
        val isShowDurationView: Boolean = false,
        val durationText: String = "",
        val startDateText: String = "",
        val startYear: Int = GregorianCalendar().get(Calendar.YEAR),
        val startMonth: Int = GregorianCalendar().get(Calendar.MONTH),
        val startDay: Int = GregorianCalendar().get(Calendar.DATE),
        val isShowStartDateView: Boolean = false,
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowToast(val msg: String) : Effect()
        data object GoBeforeFragment : Effect()
    }
}