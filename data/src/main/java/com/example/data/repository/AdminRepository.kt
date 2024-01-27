package com.example.data.repository

import com.example.data.data.AdminEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

interface AdminRepository {
    fun updateAttendanceCount(count: Int): Completable

    fun getAdminData(): Single<AdminEntity>

    fun addAdminData(adminEntity: AdminEntity): Completable
}