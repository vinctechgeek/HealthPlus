package com.example.healthplus.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.healthplus.RecipeNotes

@Database(entities = [RecipeNotes::class], version =1 )
@TypeConverters(Converters::class)
abstract class RecipeNotesDatabase2 :RoomDatabase(){

    companion object{
        const val NAME = "RecipeNotes_DB"
    }
    abstract fun getRecipeNotesDao(): RecipeNotesDao
}