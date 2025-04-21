package com.example.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.local.model.AdminLocal

@Dao
interface AdminDao {

    //최대 출석 횟수 변경
    @Query("UPDATE admin SET maxAttendance=:cnt WHERE `no`=1")
    suspend fun updateMaxAttendance(cnt: Int): Int

    @Query("SELECT * FROM ADMIN WHERE `no`=1")
    suspend fun getAdmin(): AdminLocal

    @Insert
    suspend fun addAdmin(adminLocal: AdminLocal)
}