package com.example.controldeabordaje.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.controldeabordaje.Screens.LoginScreen
import com.example.controldeabordaje.Screens.ScannerQr

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScrens.LoginScreen.route){
        composable(route = AppScrens.LoginScreen.route){
            LoginScreen(navController)
        }
        composable(route = AppScrens.ScannerQr.route){
            ScannerQr(navController)
        }
    }
}