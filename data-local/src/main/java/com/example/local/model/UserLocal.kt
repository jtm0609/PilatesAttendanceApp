package com.example.local.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.model.UserEntity
import com.example.local.LocalMapper

@Entity(tableName = "user", indices = [Index(value = ["phoneNumber"], unique = true)])
data class UserLocal(
    @PrimaryKey(autoGenerate = true)
    var no: Long = 0,
    var name: String?,
    var phoneNumber: String?,
    var duration: String?,
    var startDateTime: Long?,
    var endDateTime: Long?,
    var mileage: Int?,
    var attendanceCountOfToday: Int?,
    var attendanceDate: Long?
) : LocalMapper<UserEntity> {

    override fun toData(): UserEntity =
        UserEntity(
            no = no,
            name = name,
            phoneNumber = phoneNumber,
            duration = duration,
            startDateTime = startDateTime,
            endDateTime = endDateTime,
            mileage = mileage,
            attendanceCountOfToday = attendanceCountOfToday,
            attendanceDate = attendanceDate
        )
}

fun UserEntity.toLocal(): UserLocal =
    UserLocal(
        no = no,
        name = name,
        phoneNumber = phoneNumber,
        duration = duration,
        startDateTime = startDateTime,
        endDateTime = endDateTime,
        mileage = mileage,
        attendanceCountOfToday = attendanceCountOfToday,
        attendanceDate = attendanceDate
    )
