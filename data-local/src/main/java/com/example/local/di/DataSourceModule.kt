package com.example.local.di

import com.example.data.local.AdminDataSource
import com.example.data.local.UserDataSource
import com.example.local.impl.AdminDataSourceImpl
import com.example.local.impl.UserDataSourceImpl
import com.example.local.room.dao.AdminDao
import com.example.local.room.dao.UserDao
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