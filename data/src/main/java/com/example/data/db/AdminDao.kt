package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.model.AdminEntity

@Dao
interface AdminDao {
    //최대 출석 횟수 변경
    @Query("UPDATE admin SET maxAttendance=:cnt WHERE no=1")
    suspend fun updateMaxAttendance(cnt: Int): Int

    @Query("SELECT * FROM ADMIN WHERE no=1")
    suspend fun getAdmin(): AdminEntity

    @Insert
    suspend fun addAdmin(adminEntity: AdminEntity)
}