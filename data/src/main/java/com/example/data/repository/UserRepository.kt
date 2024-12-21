package com.example.data.repository

import com.example.data.model.UserEntity
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository {
    fun insertUser(user: UserEntity): Completable
    fun updateUserMileage(phoneNumber:String, mileage: Int): Completable
    fun getUser(phoneNumber: String): Single<UserEntity>

    fun updateUser(user: UserEntity) : Completable
}