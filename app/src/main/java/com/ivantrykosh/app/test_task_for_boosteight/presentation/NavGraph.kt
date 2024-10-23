package com.ivantrykosh.app.test_task_for_boosteight.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ivantrykosh.app.test_task_for_boosteight.presentation.homepages.Homepage1Screen
import com.ivantrykosh.app.test_task_for_boosteight.presentation.loading.LoadingScreen
import com.ivantrykosh.app.test_task_for_boosteight.presentation.onboardings.OnboardingScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.LoadingScreen.route) {
        composable(route = Screen.LoadingScreen.route) {
            LoadingScreen(navigateToOnboarding = {
                navController.navigate(Screen.OnBoardingScreen.route)
            })
        }
        composable(route = Screen.OnBoardingScreen.route) {
            OnboardingScreen(navigateToHome = {
                navController.navigate(Screen.Homepage1Screen.route)
            })
        }
        composable(route = Screen.Homepage1Screen.route) {
            Homepage1Screen(
                navigateToHomePage2 = {
                    navController.navigate(Screen.GreetingScreen.route)
                },
                navigateToHistoryPage = {
                    navController.navigate(Screen.GreetingScreen.route)
                }
            )
        }
        composable(route = Screen.GreetingScreen.route) {
            Greeting(name = "Ivan")
        }
    }
}