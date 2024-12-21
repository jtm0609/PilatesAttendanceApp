package com.example.data.datasource.local

import com.example.data.model.UserEntity
import io.reactivex.Completable
import io.reactivex.Single

interface UserDataSource {
    fun insertUser(user: UserEntity): Completable
    fun updateUserMileage(phoneNumber: String, mileage: Int): Completable
    fun updateUser(user: UserEntity): Completable

    fun getUserFromPhoneNumber(phoneNumber: String): Single<UserEntity>
}