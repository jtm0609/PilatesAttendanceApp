package com.example.data.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user", indices = [Index(value = ["phoneNumber"], unique = true)])
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var no: Long=0,
    var name: String, //이름
    var phoneNumber: String, //휴대폰 번호
    var duration: String,
    var startDateTime: Long, //운동 시작 날짜
    var endDateTime: Long, //운동 끝나는 날짜
) {
    var attendance = false //출석 여부
    var mileage = 0 //마일리지
}
