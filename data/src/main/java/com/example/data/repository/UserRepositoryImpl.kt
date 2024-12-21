package com.example.data.repository

import com.example.data.model.UserEntity
import com.example.data.datasource.local.UserDataSource
import com.orhanobut.logger.Logger
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
):UserRepository {
    override fun insertUser(user: UserEntity): Completable {
        return userDataSource.insertUser(user)
            .doOnComplete {
                Logger.d("[DB]INSERT COMPLETE")
            }
    }

    override fun updateUserMileage(phoneNumber: String, mileage: Int): Completable {
        return userDataSource.updateUserMileage(phoneNumber,mileage)
            .doOnComplete {
                Logger.d("[DB]UPDATE COMPLETE")
            }
    }

    override fun getUser(phoneNumber: String): Single<UserEntity> {
        return userDataSource.getUserFromPhoneNumber(phoneNumber)
            .doOnSuccess {
                Logger.d("[DB]SELECT: $it")
        }
    }

    override fun updateUser(user: UserEntity): Completable {
        return userDataSource.updateUser(user)
    }
}