package com.example.cmong_pilates_attendance_project.hilt

import android.content.Context
import androidx.room.Room
import com.example.data.datasource.local.AdminDataSource
import com.example.data.datasource.local.AdminDataSourceImpl
import com.example.data.datasource.local.UserDataSource
import com.example.data.datasource.local.UserDataSourceImpl
import com.example.data.db.AdminDao
import com.example.data.db.AdminDatabase
import com.example.data.db.UserDao
import com.example.data.db.UserDatabase
import com.example.data.repository.AdminRepository
import com.example.data.repository.AdminRepositoryImpl
import com.example.data.repository.UserRepository
import com.example.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    //User Database DI
    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "user.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(userDao: UserDao): UserDataSource {
        return UserDataSourceImpl(userDao)
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        userDataSource: UserDataSource
    ): UserRepository {
        return UserRepositoryImpl(userDataSource)
    }

    //Admin Database DI
    @Singleton
    @Provides
    fun provideAdminRoomDatabase(@ApplicationContext context: Context): AdminDatabase {
        return Room.databaseBuilder(
            context,
            AdminDatabase::class.java,
            "user.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
    @Singleton
    @Provides
    fun provideAdminDao(adminDatabase: AdminDatabase): AdminDao {
        return adminDatabase.adminDao()
    }

    @Singleton
    @Provides
    fun provideAdminLocalDataSource(adminDao: AdminDao): AdminDataSource {
        return AdminDataSourceImpl(adminDao)
    }

    @Singleton
    @Provides
    fun provideAdminRepository(
        adminDataSource: AdminDataSource
    ): AdminRepository {
        return AdminRepositoryImpl(adminDataSource)
    }
}