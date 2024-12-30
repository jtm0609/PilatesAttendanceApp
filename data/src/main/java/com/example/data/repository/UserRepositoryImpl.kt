package com.example.data.repository

import com.example.data.datasource.local.UserDataSource
import com.example.data.mapper.toAdvertiseEntity
import com.example.data.mapper.toDomainModel
import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import com.orhanobut.logger.Logger
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {
    override fun insertUser(user: User): Completable {
        return userDataSource.insertUser(user.toAdvertiseEntity())
            .doOnComplete {
                Logger.d("[DB]INSERT COMPLETE")
            }
    }

    override fun updateUserMileage(phoneNumber: String, mileage: Int): Completable {
        return userDataSource.updateUserMileage(phoneNumber, mileage)
            .doOnComplete {
                Logger.d("[DB]UPDATE COMPLETE")
            }
    }

    override fun getUser(phoneNumber: String): Single<User> {
        return userDataSource.getUserFromPhoneNumber(phoneNumber)
            .doOnSuccess { userEntity ->
                Logger.d("[DB]SELECT: $userEntity")
            }.map { result ->
                result.toDomainModel()
            }
    }

    override fun updateUser(user: User): Completable {
        return userDataSource.updateUser(user.toAdvertiseEntity())
    }
}