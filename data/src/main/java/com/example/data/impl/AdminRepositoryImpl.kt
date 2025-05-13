package com.example.data.impl

import com.example.data.local.AdminDataSource
import com.example.data.mapper.toDomainModel
import com.example.data.model.AdminEntity
import com.example.domain.dataresource.DataResource
import com.example.domain.model.Admin
import com.example.domain.repository.AdminRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AdminRepositoryImpl @Inject constructor(
    private val adminDataSource: AdminDataSource
) : AdminRepository {

    override suspend fun updateAttendanceCount(count: Int): Flow<DataResource<Int>> = flow {
        emit(DataResource.loading())
        try {
            adminDataSource.updateAttendanceCount(count)
            emit(DataResource.success(count))
        } catch (exception: Exception) {
            emit(DataResource.error(exception))
        }
    }

    override suspend fun getAdminData(): Flow<DataResource<Admin>> = flow {
        emit(DataResource.loading())
        try {
            val result = adminDataSource.selectAdmin()
            emit(DataResource.success(result.toDomainModel()))
        } catch (e: Exception) {
            adminDataSource.addAdmin(AdminEntity(no= 0, maxAttendance = 1))
            val result = adminDataSource.selectAdmin()
            emit(DataResource.success(result.toDomainModel()))
        }
    }
}