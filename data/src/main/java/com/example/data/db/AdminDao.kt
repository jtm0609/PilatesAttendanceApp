package com.example.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
interface AdminDao {
    //최대 출석 횟수 변경
    @Query("UPDATE admin SET maxAttendance=:cnt")
    fun updateMaxAttendance(cnt: Int)
}