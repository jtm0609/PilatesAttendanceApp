package com.example.cmong_pilates_attendance_project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.cmong_pilates_attendance_project.base.BaseViewModel

class RegisterUserViewModel : BaseViewModel() {

    //이용 기간 설정 뷰 Visible 상태
    private var _durationVisibility by mutableStateOf(false)
    val durationVisibility get() = _durationVisibility

    private var _durationText by mutableStateOf("")
    val durationText get() = _durationText

    fun setVisibilityDuration(visible: Boolean){
        _durationVisibility = visible
    }

    fun setDurationText(duration: String){
        _durationText = duration
    }
}