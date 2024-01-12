package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.data.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}