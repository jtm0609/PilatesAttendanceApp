package com.example.presentation.ui.feature.admin.register

import com.example.presentation.intent.UiEffect
import com.example.presentation.intent.UiEvent
import com.example.presentation.intent.UiState
import java.util.Calendar
import java.util.GregorianCalendar

class RegisterUserContract {
    sealed class Event : UiEvent {
        data class OnChangedName(val name: String) : Event()  // 이름 변경 이벤트
        data class OnChangePhoneNumber(val phone: String) : Event()  // 전화번호 변경 이벤트
        data class OnChangeDuration(val duration: String) : Event()  // 기간 변경 이벤트
        data class OnCompleteSetDuration(val duration: String) : Event() //이용기간 설정 완료 이벤트
        data class OnCompleteStartDate(val year: Int, val month: Int, val day: Int) :
            Event() //이용기간 설정 완료 이벤트
        data class ClickedSave(
            val name: String,
            val phone: String,
            val durationText: String,
            val startDateText: String
        ) : Event() //저장 이벤트

        data object OnClickSetDuration : Event() //이용기간 설정 이벤트
        data object OnClickSetBeginDate : Event() //이용시작일 설정 이벤트
        data object OnDismissStartDate : Event() //이용기간 설정 완료 이벤트
        data object OnDismissDurationView : Event()
    }

    data class State(
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