package com.example.healthplus

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@Composable
fun MainNavigation(navViewModel: NavigationViewModel) {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(
        initialValue =
        DrawerValue.Closed
    )
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(menus) { route ->
                    coroutineScope.launch {
                        drawerState.close()
                    }
                    navController.navigate(route)
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.Home.value,
            modifier = Modifier.padding(8.dp)
        ) {
            composable(Routes.Home.value) {
                Home(drawerState)
            }
            composable(Routes.RecipeNotes.value) {
                RecipeNotes(drawerState)
            }
            composable(Routes.CalculateNutrients.value) {
                CalculateNutrients(drawerState)
            }
        }
    }
}
