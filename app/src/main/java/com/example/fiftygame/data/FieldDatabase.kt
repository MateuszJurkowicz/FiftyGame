package com.example.fiftygame.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Field::class], version = 1, exportSchema = false)
abstract class FieldDatabase : RoomDatabase() {
    abstract fun fieldDao(): FieldDao

    companion object {
        @Volatile
        private var INSTANCE: FieldDatabase? = null
        fun getDatabase(context: Context): FieldDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FieldDatabase::class.java,
                    "field_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}