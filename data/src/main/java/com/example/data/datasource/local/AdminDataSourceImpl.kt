package com.example.data.datasource.local

import com.example.data.model.AdminEntity
import com.example.data.db.AdminDao
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AdminDataSourceImpl @Inject constructor(
    private val adminDao: AdminDao
) : AdminDataSource {
    override fun updateAttendanceCount(count: Int): Completable {
        return adminDao.updateMaxAttendance(count)
    }

    override fun selectAdmin(): Single<AdminEntity> {
        return adminDao.getAdmin()
    }

    override fun addAdmin(adminEntity: AdminEntity): Completable {
        return adminDao.addAdmin(adminEntity)
    }
}