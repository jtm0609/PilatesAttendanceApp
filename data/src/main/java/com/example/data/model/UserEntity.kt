package com.example.data.model

data class UserEntity(
    var no: Long = 0,
    var name: String?,
    var phoneNumber: String?,
    var duration: String?,
    var startDateTime: Long?,
    var endDateTime: Long?,
    var mileage: Int?,
    var attendanceCountOfToday: Int?,
    var attendanceDate: Long?
)
