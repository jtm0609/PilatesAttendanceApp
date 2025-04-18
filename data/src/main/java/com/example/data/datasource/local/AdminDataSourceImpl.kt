package com.example.data.datasource.local

import com.example.data.model.AdminEntity
import com.example.data.db.AdminDao
import javax.inject.Inject

class AdminDataSourceImpl @Inject constructor(
    private val adminDao: AdminDao
) : AdminDataSource {
    override suspend fun updateAttendanceCount(count: Int) {
        adminDao.updateMaxAttendance(count)
    }

    override suspend fun selectAdmin(): AdminEntity {
        return adminDao.getAdmin()
    }

    override suspend fun addAdmin(adminEntity: AdminEntity) {
        adminDao.addAdmin(adminEntity)
    }
}