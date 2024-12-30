package com.example.domain.model

import com.example.domain.utils.toDateString
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id : Long,
    var name: String, //이름
    var phoneNumber: String, //휴대폰 번호
    var duration: String, //등록 기간
    var startDateTime: Long, //운동 시작 날짜
    var endDateTime: Long, //운동 끝나는 날짜
    var mileage: Int, //마일리지
    var attendanceCountOfToday: Int, //출석한 횟수(오늘)
    var attendanceDate: Long //출석한 날짜
) {
    fun getStartDate(): Date {
        val dateString = startDateTime.toDateString()
        val (year, month, day) = dateString.split("-").map{it.toInt()}
        return Date(year, month - 1, day)
    }

    fun getStartDateText(): String {
        return startDateTime.toDateString()
    }
}
