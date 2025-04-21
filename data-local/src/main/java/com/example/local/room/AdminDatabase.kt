package com.example.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.local.model.AdminLocal
import com.example.local.room.dao.AdminDao

@Database(entities = [AdminLocal::class], version = 3, exportSchema = false)
abstract class AdminDatabase : RoomDatabase() {
    abstract fun adminDao(): AdminDao
}