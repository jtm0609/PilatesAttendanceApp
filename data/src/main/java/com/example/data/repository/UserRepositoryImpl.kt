package com.example.data.repository

import com.example.data.datasource.local.UserDataSource
import com.example.data.mapper.toAdvertiseEntity
import com.example.data.mapper.toDomainModel
import com.example.domain.dataresource.DataResource
import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import com.orhanobut.logger.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {
    override suspend fun insertUser(user: User): Flow<DataResource<Long>> = flow {
        emit(DataResource.loading())
        try {
            emit(DataResource.success(userDataSource.insertUser(user.toAdvertiseEntity())))
        } catch (exception: Exception) {
            emit(DataResource.error(exception))
        }
    }

    override suspend fun updateUserMileage(phoneNumber: String, mileage: Int) = flow {
        emit(DataResource.loading())
        try {
            userDataSource.updateUserMileage(phoneNumber, mileage)
        } catch (exception: Exception) {
            emit(DataResource.error(exception))
        }
    }

    override suspend fun getUser(phoneNumber: String): Flow<DataResource<User>> = flow {
        emit(DataResource.loading())
        try {
            val userEntity = userDataSource.getUserFromPhoneNumber(phoneNumber)
            emit(DataResource.success(userEntity.toDomainModel()))
        } catch (exception: Exception) {
            emit(DataResource.error(exception))
        }
    }

    override suspend fun updateUser(user: User): Flow<DataResource<Int>> = flow {
        emit(DataResource.loading())
        try {
            emit(DataResource.success(userDataSource.updateUser(user.toAdvertiseEntity())))
        } catch (exception: Exception) {
            emit(DataResource.error(exception))
        }
    }
}