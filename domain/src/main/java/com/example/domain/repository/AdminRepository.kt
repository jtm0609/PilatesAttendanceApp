package com.example.domain.repository

import com.example.domain.dataresource.DataResource
import com.example.domain.model.Admin
import kotlinx.coroutines.flow.Flow

interface AdminRepository {
    suspend fun updateAttendanceCount(count: Int): Flow<DataResource<Int>>
    suspend fun getAdminData(): Flow<DataResource<Admin>>
}