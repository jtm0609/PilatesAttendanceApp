package com.example.local.di

import android.content.Context
import androidx.room.Room
import com.example.local.room.AdminDatabase
import com.example.local.room.UserDatabase
import com.example.local.room.dao.AdminDao
import com.example.local.room.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): UserDatabase =
        Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "user.db"
        ).fallbackToDestructiveMigration()
            .build()


    @Singleton
    @Provides
    fun provideAdminRoomDatabase(@ApplicationContext context: Context): AdminDatabase =
        Room.databaseBuilder(
            context,
            AdminDatabase::class.java,
            "admin.db"
        ).fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideUserDao(userDatabase: UserDatabase): UserDao =
        userDatabase.userDao()

    @Singleton
    @Provides
    fun provideAdminDao(adminDatabase: AdminDatabase): AdminDao =
        adminDatabase.adminDao()
}