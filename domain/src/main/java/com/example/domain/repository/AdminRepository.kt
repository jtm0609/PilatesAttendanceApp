package com.example.domain.repository

import com.example.domain.model.Admin
import io.reactivex.Completable
import io.reactivex.Single

interface AdminRepository {
    fun updateAttendanceCount(count: Int): Completable
    fun getAdminData(): Single<Admin>
}