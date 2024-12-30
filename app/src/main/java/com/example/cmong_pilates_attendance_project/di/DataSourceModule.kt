package com.example.cmong_pilates_attendance_project.di

import com.example.data.datasource.local.AdminDataSource
import com.example.data.datasource.local.AdminDataSourceImpl
import com.example.data.datasource.local.UserDataSource
import com.example.data.datasource.local.UserDataSourceImpl
import com.example.data.db.AdminDao
import com.example.data.db.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(userDao: UserDao): UserDataSource {
        return UserDataSourceImpl(userDao)
    }

    @Singleton
    @Provides
    fun provideAdminLocalDataSource(adminDao: AdminDao): AdminDataSource {
        return AdminDataSourceImpl(adminDao)
    }
}