package com.example.data.datasource.local

import com.example.data.data.UserEntity
import com.example.data.db.UserDao
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
  private val userDao: UserDao
):UserDataSource {
    override fun insertUser(user: UserEntity): Completable {
       return userDao.insertUser(user)
    }

    override fun updateUserMileage(phoneNumber: String, mileage: Int): Completable {
        return userDao.updateMileage(phoneNumber,mileage)
    }

    override fun updateUser(
        user: UserEntity
    ): Completable {
        return userDao.updateUser(user)
    }

    override fun getUserFromPhoneNumber(phoneNumber: String): Single<UserEntity> {
       return userDao.selectUserFromPNumber(phoneNumber)
    }

}