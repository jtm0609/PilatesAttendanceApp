package com.example.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.model.AdminEntity
import com.example.local.LocalMapper

@Entity(tableName = "admin")
data class AdminLocal(
    @PrimaryKey(autoGenerate = true)
    var no: Long = 1,
    var maxAttendance: Int? //하루 최대 출석 횟수
) : LocalMapper<AdminEntity> {
    override fun toData(): AdminEntity =
        AdminEntity(
            no = no,
            maxAttendance = maxAttendance
        )
}

fun AdminEntity.toLocal(): AdminLocal =
    AdminLocal(
        no = no,
        maxAttendance = maxAttendance
    )
