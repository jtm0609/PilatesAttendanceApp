package com.example.local.impl

import com.example.data.local.UserDataSource
import com.example.data.model.UserEntity
import com.example.local.model.toLocal
import com.example.local.room.dao.UserDao
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userDao: UserDao
) : UserDataSource {

    override suspend fun insertUser(user: UserEntity): Long =
        userDao.insertUser(user.toLocal())

    override suspend fun updateUserMileage(phoneNumber: String, mileage: Int): Int =
        userDao.updateMileage(phoneNumber, mileage)


    override suspend fun updateUser(user: UserEntity): Int =
        userDao.updateUser(user.toLocal())


    override suspend fun getUserFromPhoneNumber(phoneNumber: String): UserEntity =
        userDao.getUserFromPhoneNumber(phoneNumber).toData()
}