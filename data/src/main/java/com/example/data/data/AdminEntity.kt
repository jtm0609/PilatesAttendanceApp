package com.example.data.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "admin")
data class AdminEntity(

    @PrimaryKey
    val no: Long,
    val maxAttendance: Int //하루 최대 출석 횟수
)
