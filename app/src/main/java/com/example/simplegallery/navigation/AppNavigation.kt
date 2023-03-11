package com.example.simplegallery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.simplegallery.screens.DetailScreen
import com.example.simplegallery.screens.HomeScreen


@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.DetailsScreen.name){

             composable(AppScreens.HomeScreen.name){
                 HomeScreen(navController = navController)
             }


            composable(AppScreens.DetailsScreen.name+"/{DATA}", arguments = listOf( navArgument(name="DATA") {type = NavType.StringType} )){ backStackEntry ->

                DetailScreen(navController = navController,backStackEntry.arguments?.getString("DATA"))
            }


    }
}