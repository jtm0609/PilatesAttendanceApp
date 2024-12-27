package com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.reregister

import com.example.cmong_pilates_attendance_project.intent.UiEffect
import com.example.cmong_pilates_attendance_project.intent.UiEvent
import com.example.cmong_pilates_attendance_project.intent.UiState
import com.example.data.model.UserEntity
import java.util.Calendar
import java.util.GregorianCalendar

class ReregisterUserContract {
    sealed class Event : UiEvent {
        data class OnChangedDuration(val duration: String) : Event()  // 기간 변경 이벤트
        data object OnClickSetDuration : Event() //이용기간 설정 이벤트
        data class OnCompleteSetDuration(val duration: String) : Event() //이용기간 설정 완료 이벤트
        data object OnClickSetBeginDate : Event() //이용시작일 설정 이벤트
        data class OnCompleteStartDate(val year: Int, val month: Int, val day: Int) :
            Event() //이용기간 설정 완료 이벤트
        data object OnDismissStartDate : Event() //이용기간 설정 완료 이벤트
        data object OnDismissDurationView: Event()

        data class ClickedSave(
            val no: Long,
            val name: String,
            val phone: String,
            val durationText: String,
            val startDateText: String
        ) : Event() //저장 이벤트
    }

    data class State(
        //이용 기간 설정 뷰 Visible 상태
        val no: Long =0,
        val name: String = "",
        val phone: String = "",
        val durationValues: List<String> = listOf("2주", "4주", "8주", "12주"),
        val durationState: String = durationValues[0],
        val durationText: String = "",
        val startYear: Int = GregorianCalendar().get(Calendar.YEAR),
        val startMonth: Int = GregorianCalendar().get(Calendar.MONTH),
        val startDay: Int = GregorianCalendar().get(Calendar.DATE),
        val startDateText: String = "",
        val isShowDurationView: Boolean = false,

        val isShowStartDateView: Boolean = false,
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowToast(val msg: String) : Effect()
        data class CompleteRegister(val user: UserEntity) : Effect()
    }
}