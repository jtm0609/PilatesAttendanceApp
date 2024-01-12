package com.example.cmong_pilates_attendance_project.hilt

import android.content.Context
import androidx.room.Room
import com.example.data.datasource.local.UserDataSource
import com.example.data.datasource.local.UserDataSourceImpl
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
object DataModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): UserDatabase{
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "user.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserDao(userDatabase: UserDatabase):UserDao{
        return userDatabase.userDao()
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(userDao: UserDao): UserDataSource{
        return UserDataSourceImpl(userDao)
    }
}