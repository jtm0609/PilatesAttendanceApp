package com.example.data.di

import com.example.domain.repository.AdminRepository
import com.example.data.impl.AdminRepositoryImpl
import com.example.domain.repository.UserRepository
import com.example.data.impl.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    abstract fun bindAdminRepository(adminRepositoryImpl: AdminRepositoryImpl): AdminRepository
}