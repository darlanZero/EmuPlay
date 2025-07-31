package com.starsford.wabemuplay.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController)
        }
        composable(
            "emulator/{gamePath}",
            arguments = listOf(navArgument("gamePath") { type = NavType.StringType })
        ) { backStackEntry ->
            val gamePath = backStackEntry.arguments?.getString("gamePath") ?: ""
            EmulatorScreen(gamePath = gamePath)
        }
    }
}