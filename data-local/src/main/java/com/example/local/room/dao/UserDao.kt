package com.example.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.local.model.UserLocal

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: UserLocal): Long

    @Query("UPDATE user SET mileage=:mileage WHERE phoneNumber=:phoneNumber")
    suspend fun updateMileage(phoneNumber: String, mileage: Int): Int

    @Update
    suspend fun updateUser(user: UserLocal): Int

    @Query("SELECT * FROM user WHERE phoneNumber=:phoneNumber")
    suspend fun getUserFromPhoneNumber(phoneNumber: String): UserLocal

    @Query("SELECT * FROM user")
    suspend fun getUsers(): List<UserLocal>
}