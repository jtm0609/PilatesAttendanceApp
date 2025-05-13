package com.example.data.mapper

import com.example.data.model.AdminEntity
import com.example.data.model.UserEntity
import com.example.domain.model.Admin
import com.example.domain.model.User


fun User.toAdvertiseEntity(): UserEntity =
    UserEntity(
        no = id,
        name = name,
        phoneNumber = phoneNumber,
        duration = duration,
        startDateTime = startDateTime,
        endDateTime = endDateTime,
        mileage = mileage,
        attendanceCountOfToday = attendanceCountOfToday,
        attendanceDate = attendanceDate
    )

fun Admin.toContentEntity(): AdminEntity {
    return AdminEntity(
        maxAttendance = maxAttendance
    )
}
