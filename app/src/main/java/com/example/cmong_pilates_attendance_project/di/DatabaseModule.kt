package com.example.cmong_pilates_attendance_project.di

import android.content.Context
import androidx.room.Room
import com.example.data.db.AdminDao
import com.example.data.db.AdminDatabase
import com.example.data.db.UserDao
import com.example.data.db.UserDatabase
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
    fun provideAdminRoomDatabase(@ApplicationContext context: Context): AdminDatabase {
        return Room.databaseBuilder(
            context,
            AdminDatabase::class.java,
            "admin.db"
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
    fun provideAdminDao(adminDatabase: AdminDatabase): AdminDao {
        return adminDatabase.adminDao()
    }
}