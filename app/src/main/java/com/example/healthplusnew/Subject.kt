package com.example.healthplusnew

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Subject(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val name: String,
    val content : String
)