package com.example.healthplus.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.healthplus.RecipeNotes


@Dao
interface RecipeNotesDao {

    @Query ("SELECT * FROM RECIPENOTES ORDER BY createdAt DESC")
    fun getAllRecipeNotes() : LiveData<List<RecipeNotes>>

    @Insert
    fun addRecipeNotes(recipeNotes: RecipeNotes)

    @Query("DELETE FROM RECIPENOTES WHERE id = :id")
    fun deleteRecipeNotes(id: Int)
}