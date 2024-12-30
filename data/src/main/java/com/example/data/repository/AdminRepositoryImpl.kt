package com.example.data.repository

import com.example.data.datasource.local.AdminDataSource
import com.example.data.mapper.toDomainModel
import com.example.data.model.AdminEntity
import com.example.domain.model.Admin
import com.example.domain.repository.AdminRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AdminRepositoryImpl @Inject constructor(
    private val adminDataSource: AdminDataSource
) : AdminRepository {
    override fun updateAttendanceCount(count: Int): Completable {
        return adminDataSource.updateAttendanceCount(count)
    }

    override fun getAdminData(): Single<Admin> {
        return adminDataSource.selectAdmin().map { result ->
            result.toDomainModel()
        }.onErrorResumeNext { throwable->
            adminDataSource.addAdmin(AdminEntity(no= 0, maxAttendance = 1))
                .andThen(adminDataSource.selectAdmin())
                .map { result -> result.toDomainModel() }
        }
    }
}