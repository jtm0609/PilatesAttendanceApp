package com.example.presentation.ui.feature.attendance.complete

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.example.presentation.base.BaseViewModel
import com.example.presentation.navigation.AppDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AttendanceCompleteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<AttendanceCompleteContract.Event,
        AttendanceCompleteContract.State,
        AttendanceCompleteContract.Effect>() {

    private val _userName: String = savedStateHandle.toRoute<AppDestination.AttendanceComplete>().name
    private val _userMileage: Int = savedStateHandle.toRoute<AppDestination.AttendanceComplete>().mileage

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