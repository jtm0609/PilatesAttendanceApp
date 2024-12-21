package com.example.data.datasource.local

import com.example.data.model.AdminEntity
import io.reactivex.Completable
import io.reactivex.Single

interface AdminDataSource {
    fun updateAttendanceCount(count:Int): Completable

    fun selectAdmin(): Single<AdminEntity>

    fun addAdmin(adminEntity: AdminEntity): Completable
}