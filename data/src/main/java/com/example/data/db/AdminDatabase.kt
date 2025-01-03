package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.AdminEntity

@Database(entities = [AdminEntity::class], version = 3, exportSchema = false)
abstract class AdminDatabase : RoomDatabase() {
    abstract fun adminDao(): AdminDao
}