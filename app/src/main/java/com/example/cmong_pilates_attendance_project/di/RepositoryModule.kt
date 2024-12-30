package com.example.cmong_pilates_attendance_project.di

import com.example.data.datasource.local.AdminDataSource
import com.example.data.datasource.local.UserDataSource
import com.example.domain.repository.AdminRepository
import com.example.data.repository.AdminRepositoryImpl
import com.example.domain.repository.UserRepository
import com.example.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(
        userDataSource: UserDataSource
    ): UserRepository {
        return UserRepositoryImpl(userDataSource)
    }

    @Singleton
    @Provides
    fun provideAdminRepository(
        adminDataSource: AdminDataSource
    ): AdminRepository {
        return AdminRepositoryImpl(adminDataSource)
    }
}