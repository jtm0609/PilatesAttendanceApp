package com.example.cmong_pilates_attendance_project.state

sealed class PilatesState {
    data object Success : PilatesState()
    data object Fail: PilatesState()
}
