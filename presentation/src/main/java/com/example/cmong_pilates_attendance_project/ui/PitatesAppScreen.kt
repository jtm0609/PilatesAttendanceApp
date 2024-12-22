package com.example.cmong_pilates_attendance_project.ui

sealed class Routes {
    AttendanceMain :,
    AttendanceComplete,
    AdminMenu,
    ChangeAttendanceCount,
    ChangeUserMileage,
    InputPhoneNumber,
    ManageUser,
    RegisterUser,
    ReregisterUser
}