package com.example.data.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user", indices = [Index(value = ["phoneNumber"], unique = true)])
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var no: Long = 0,
    var name: String, //이름
    var phoneNumber: String, //휴대폰 번호
    var duration: String, //등록 기간
    var startDateTime: Long, //운동 시작 날짜
    var endDateTime: Long, //운동 끝나는 날짜
) {
    //var attendance = false //출석 여부
    var mileage = 0 //마일리지
    var attendanceCountOfToday = 0 //출석한 횟수(오늘)
    var attendanceDate: Long = 0 //출석한 날짜
}
