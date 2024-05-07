package com.example.healthplus

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.healthplus.db.RecipeNotesDatabase
class MainApplication : Application() {
    companion object {
        lateinit var recipeNotesDatabase: RecipeNotesDatabase

        // Define the migration from version 1 to version 2
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Assuming the addition of a 'content' column
                database.execSQL("ALTER TABLE RECIPENOTES ADD COLUMN content TEXT NOT NULL DEFAULT ''")
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        // Build the database with the necessary migrations
        recipeNotesDatabase = Room.databaseBuilder(
            applicationContext,
            RecipeNotesDatabase::class.java,
            RecipeNotesDatabase.NAME
        )
            .addMigrations(MIGRATION_1_2) // Add this line to include the migration
            .build()
    }
}
