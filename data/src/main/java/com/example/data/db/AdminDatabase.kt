package com.example.data.db

import androidx.room.Database
import com.example.data.data.AdminEntity

@Database(entities = [AdminEntity::class], version = 1, exportSchema = false)
abstract class AdminDatabase {
    abstract fun adminDao(): AdminDao
}