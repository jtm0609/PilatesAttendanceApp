package com.example.data.datasource.local

import com.example.data.data.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface UserDataSource {
    fun insertUser(user:UserEntity): Completable
    fun updateUserMileage(phoneNumber:String, mileage:Int): Completable
    fun getUserFromPhoneNumber(phoneNumber: String): Single<UserEntity>
}