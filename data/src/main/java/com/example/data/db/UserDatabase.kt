package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.UserEntity

@Database(entities = [UserEntity::class], version = 7, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
}