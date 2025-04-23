package com.example.attendance.complete

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.example.core_android.base.BaseViewModel
import com.example.core_android.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AttendanceCompleteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<AttendanceCompleteContract.Event,
        AttendanceCompleteContract.State,
        AttendanceCompleteContract.Effect>() {

    private val _userName: String = savedStateHandle.toRoute<Route.AttendanceComplete>().name
    private val _userMileage: Int = savedStateHandle.toRoute<Route.AttendanceComplete>().mileage

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