package com.example.data.db

import androidx.room.Database
import com.example.data.data.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase {
    abstract fun userDao(): UserDao
}