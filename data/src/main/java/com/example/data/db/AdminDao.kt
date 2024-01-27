package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.data.AdminEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface AdminDao {
    //최대 출석 횟수 변경
    @Query("UPDATE admin SET maxAttendance=:cnt WHERE no=1")
    fun updateMaxAttendance(cnt: Int): Completable

    @Query("SELECT * FROM ADMIN WHERE no=1")
    fun selectAdmin(): Single<AdminEntity>

    @Insert
    fun addAdmin(adminEntity: AdminEntity): Completable
}