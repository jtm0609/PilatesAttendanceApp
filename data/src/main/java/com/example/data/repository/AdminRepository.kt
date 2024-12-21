package com.example.data.repository

import com.example.data.model.AdminEntity
import io.reactivex.Completable
import io.reactivex.Single

interface AdminRepository {
    fun updateAttendanceCount(count: Int): Completable

    fun getAdminData(): Single<AdminEntity>

    fun addAdminData(adminEntity: AdminEntity): Completable
}