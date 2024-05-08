package com.example.healthplusnew

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Subject::class], version = 2, exportSchema = false)
abstract class SubjectDatabase : RoomDatabase() {
    abstract fun subjectDAO(): SubjectDAO
    companion object {
        @Volatile
        private var INSTANCE: SubjectDatabase? = null
        fun getDatabase(context: Context): SubjectDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SubjectDatabase::class.java,
                    "subject_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}