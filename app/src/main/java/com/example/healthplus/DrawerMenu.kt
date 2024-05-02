package com.example.healthplus

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerMenu(
    val icon: ImageVector, val title: String, val

    route: String
)

val menus = arrayOf(
    DrawerMenu(Icons.Filled.Home, "Home", Routes.Home.value),
    DrawerMenu(Icons.Filled.Person, "My Recipes", Routes.RecipeNotes.value),
    DrawerMenu(Icons.Filled.Info, "Calculate Nutrients", Routes.CalculateNutrients.value)
)