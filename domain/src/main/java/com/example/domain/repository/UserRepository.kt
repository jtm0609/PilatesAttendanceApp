package com.example.domain.repository

import com.example.domain.model.User
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository {
    fun insertUser(user: User): Completable
    fun updateUserMileage(phoneNumber:String, mileage: Int): Completable
    fun getUser(phoneNumber: String): Single<User>
    fun updateUser(user: User) : Completable
}