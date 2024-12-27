package com.example.cmong_pilates_attendance_project.ui.feature.attendance.complete

import androidx.lifecycle.SavedStateHandle
import com.example.cmong_pilates_attendance_project.base.BaseViewModel
import com.example.cmong_pilates_attendance_project.ui.navigation.NavArgument.ARG_USER_MILEAGE
import com.example.cmong_pilates_attendance_project.ui.navigation.NavArgument.ARG_USER_NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AttendanceCompleteViewModel
@Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<AttendanceCompleteContract.Event,
        AttendanceCompleteContract.State,
        AttendanceCompleteContract.Effect>() {

    val _userName: String = savedStateHandle[ARG_USER_NAME] ?: "null"
    val _userMileage: Int = savedStateHandle[ARG_USER_MILEAGE] ?: 0

    init {
        setState {
            this.copy(
                name = _userName,
                mileage = _userMileage
            )
        }
    }

    override fun createInitialState(): AttendanceCompleteContract.State {
        return AttendanceCompleteContract.State()
    }

    override fun handleEvent(event: AttendanceCompleteContract.Event) {

    }
}