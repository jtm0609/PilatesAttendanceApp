package com.example.data.datasource.local

import com.example.data.model.AdminEntity

interface AdminDataSource {
    suspend fun updateAttendanceCount(count:Int)
    suspend fun selectAdmin(): AdminEntity
    suspend fun addAdmin(adminEntity: AdminEntity)
}