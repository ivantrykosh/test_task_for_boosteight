package com.ivantrykosh.app.test_task_for_boosteight.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ivantrykosh.app.test_task_for_boosteight.presentation.loading.LoadingScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.LoadingScreen.route) {
        composable(route = Screen.LoadingScreen.route) {
            LoadingScreen(navigateToOnBoarding = {
                navController.navigate(Screen.GreetingScreen.route)
            })
        }
        composable(route = Screen.GreetingScreen.route) {
            Greeting(name = "Ivan")
        }
    }
}