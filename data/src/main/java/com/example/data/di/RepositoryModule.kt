package com.example.data.di

import com.example.domain.repository.AdminRepository
import com.example.data.impl.AdminRepositoryImpl
import com.example.domain.repository.UserRepository
import com.example.data.impl.UserRepositoryImpl
import com.example.data.local.AdminDataSource
import com.example.data.local.UserDataSource
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