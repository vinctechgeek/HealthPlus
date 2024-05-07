package com.example.healthplus

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date

@Entity
data class RecipeNotes(
    @PrimaryKey(autoGenerate = true)
    var id:  Int =0,
    var title:  String,
    var content: String,
    var createdAt: Date
)
