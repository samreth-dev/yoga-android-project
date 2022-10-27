package com.example.yoga

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [YogaSession::class], version = 2)
@TypeConverters(DateConverter::class)
abstract class YogaDatabase() : RoomDatabase() { // Must Inherit from RoomDatabase
    abstract fun getYogaSessionDao(): YogaSessionDao

    // Build RoomDB
    companion object {
        @Volatile
        private var instance: YogaDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        // Function to build database
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            YogaDatabase::class.java,
            "yogadatabase"
        ).build()
    }
}
