package com.example.data.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val no: Long=0,
    val name: String, //이름
    val phoneNumber: String, //휴대폰 번호
    val startDate: Long, //운동 시작 날짜
    val endDate: Long, //운동 끝나는 날짜
) {
    var attendance = false //출석 여부
    var mileage = 0 //마일리지
}
