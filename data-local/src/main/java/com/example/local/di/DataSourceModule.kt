package com.example.local.di

import com.example.data.local.AdminDataSource
import com.example.data.local.UserDataSource
import com.example.local.impl.AdminDataSourceImpl
import com.example.local.impl.UserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindLocalDataSource(userDataSourceImpl: UserDataSourceImpl) : UserDataSource

    @Singleton
    @Binds
    abstract fun bindAdminLocalDataSource(adminDataSourceImpl: AdminDataSourceImpl): AdminDataSource
}