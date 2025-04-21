package com.example.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.local.model.UserLocal
import com.example.local.room.dao.UserDao

@Database(entities = [UserLocal::class], version = 7, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}