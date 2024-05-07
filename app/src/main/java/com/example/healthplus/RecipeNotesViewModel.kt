package com.example.healthplus

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthplus.db.RecipeNotesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class RecipeNotesViewModel : ViewModel() {

    val recipeNotesDao = MainApplication.recipeNotesDatabase.getRecipeNotesDao()


    val recipenotesList : LiveData<List<RecipeNotes>> = recipeNotesDao.getAllRecipeNotes()


//    @RequiresApi(Build.VERSION_CODES.O)
//    fun addRecipeNotes(title:String){
//
//        viewModelScope.launch(Dispatchers.IO) {
//            recipeNotesDao.addRecipeNotes(RecipeNotes(title=title,createdAt = Date.from(Instant.now())))
//        }
//
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addRecipeNotes(title:String,content:String){

        viewModelScope.launch(Dispatchers.IO) {
            recipeNotesDao.addRecipeNotes(RecipeNotes(title=title,content=content,createdAt = Date.from(Instant.now())))
        }

    }
    fun deleteRecipeNotes(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            recipeNotesDao.deleteRecipeNotes(id)
        }
    }

}