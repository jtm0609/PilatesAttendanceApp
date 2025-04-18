package com.example.data.datasource.local

import com.example.data.model.UserEntity
import com.example.data.db.UserDao
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
  private val userDao: UserDao
):UserDataSource {
    override suspend fun insertUser(user: UserEntity): Long {
       return userDao.insertUser(user)
    }

    override suspend fun updateUserMileage(phoneNumber: String, mileage: Int): Int {
        return userDao.updateMileage(phoneNumber,mileage)
    }

    override suspend fun updateUser(user: UserEntity): Int {
        return userDao.updateUser(user)
    }

    override suspend fun getUserFromPhoneNumber(phoneNumber: String): UserEntity {
       return userDao.getUserFromPhoneNumber(phoneNumber)
    }
}