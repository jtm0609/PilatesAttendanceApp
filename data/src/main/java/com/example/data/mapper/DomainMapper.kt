package com.example.data.mapper

import com.example.data.model.AdminEntity
import com.example.data.model.UserEntity
import com.example.domain.model.Admin
import com.example.domain.model.User

fun UserEntity.toDomainModel(): User {
    return User(
        id = no,
        name = name ?: "null",
        phoneNumber = phoneNumber ?: "null",
        duration = duration ?: "null",
        startDateTime = startDateTime ?: 0,
        endDateTime = endDateTime ?: 0,
        mileage = mileage ?: 0,
        attendanceCountOfToday = attendanceCountOfToday ?: 0,
        attendanceDate = attendanceDate ?: 0
    )
}

fun AdminEntity.toDomainModel(): Admin {
    return Admin(
        maxAttendance = maxAttendance ?: 0
    )
}