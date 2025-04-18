package com.example.domain.repository

import com.example.domain.dataresource.DataResource
import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: User): Flow<DataResource<Long>>
    suspend fun updateUserMileage(phoneNumber:String, mileage: Int): Flow<DataResource<Int>>
    suspend fun getUser(phoneNumber: String): Flow<DataResource<User>>
    suspend fun updateUser(user: User): Flow<DataResource<Int>>
}