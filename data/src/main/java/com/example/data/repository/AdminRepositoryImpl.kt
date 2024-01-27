package com.example.data.repository

import com.example.data.data.AdminEntity
import com.example.data.datasource.local.AdminDataSource
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AdminRepositoryImpl @Inject constructor(
    private val adminDataSource: AdminDataSource
): AdminRepository {
    override fun updateAttendanceCount(count: Int): Completable {
        return adminDataSource.updateAttendanceCount(count)
    }

    override fun getAdminData(): Single<AdminEntity> {
        return adminDataSource.selectAdmin()
    }

    override fun addAdminData(adminEntity: AdminEntity): Completable {
        return adminDataSource.addAdmin(adminEntity)
    }
}