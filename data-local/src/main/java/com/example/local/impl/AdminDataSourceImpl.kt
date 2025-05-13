package com.example.local.impl

import com.example.data.local.AdminDataSource
import com.example.data.model.AdminEntity
import com.example.local.model.toLocal
import com.example.local.room.dao.AdminDao
import javax.inject.Inject

class AdminDataSourceImpl @Inject constructor(
    private val adminDao: AdminDao
) : AdminDataSource {

    override suspend fun updateAttendanceCount(count: Int) {
        adminDao.updateMaxAttendance(count)
    }

    override suspend fun selectAdmin(): AdminEntity =
        adminDao.getAdmin().toData()

    override suspend fun addAdmin(adminEntity: AdminEntity) {
        adminDao.addAdmin(adminEntity.toLocal())
    }
}