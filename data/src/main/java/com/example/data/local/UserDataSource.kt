package com.example.data.local

import com.example.data.model.UserEntity

interface UserDataSource {
    suspend fun insertUser(user: UserEntity): Long
    suspend fun updateUserMileage(phoneNumber: String, mileage: Int): Int
    suspend fun updateUser(user: UserEntity): Int
    suspend fun getUserFromPhoneNumber(phoneNumber: String): UserEntity
}