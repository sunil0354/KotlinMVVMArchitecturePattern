package com.sunil0354.demo.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sunil0354.demo.repository.model.TestListItem

/**
 * App Database
 * Define all entities and access doa's here/ Each entity is a table.
 */
@Database(entities = [TestListItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Get DAO's
     */
    abstract fun mainDao(): MainDao
}