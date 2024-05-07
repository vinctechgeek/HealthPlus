package com.example.healthplus.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.healthplus.RecipeNotes

@Database(entities = [RecipeNotes::class], version =2 )
@TypeConverters(Converters::class)
abstract class RecipeNotesDatabase :RoomDatabase(){

    companion object{
        const val NAME = "RecipeNotes_DB"
    }
    abstract fun getRecipeNotesDao(): RecipeNotesDao
}