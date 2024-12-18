package com.ivantrykosh.app.test_task_for_boosteight.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ivantrykosh.app.test_task_for_boosteight.presentation.history.ResultHistoryScreen
import com.ivantrykosh.app.test_task_for_boosteight.presentation.homepages.Homepage1Screen
import com.ivantrykosh.app.test_task_for_boosteight.presentation.homepages.Homepage2Screen
import com.ivantrykosh.app.test_task_for_boosteight.presentation.loading.LoadingScreen
import com.ivantrykosh.app.test_task_for_boosteight.presentation.onboardings.OnboardingScreen
import com.ivantrykosh.app.test_task_for_boosteight.presentation.result.ResultScreen
import com.ivantrykosh.app.test_task_for_boosteight.utils.AppPreferences

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.LoadingScreen.route) {
        composable(route = Screen.LoadingScreen.route) {
            LoadingScreen(navigateToOnboardingOrHomePage1 = {
                // Track is it's first session and choose corresponding screen user navigates to
                if (AppPreferences.isFirstSession == true) {
                    AppPreferences.isFirstSession = false
                    navController.navigate(Screen.OnBoardingScreen.route) {
                        popUpTo(Screen.LoadingScreen.route) { inclusive = true }
                    }
                } else {
                    navController.navigate(Screen.Homepage1Screen.route) {
                        popUpTo(Screen.LoadingScreen.route) { inclusive = true }
                    }
                }
            })
        }
        composable(route = Screen.OnBoardingScreen.route) {
            OnboardingScreen(navigateToHome = {
                navController.navigate(Screen.Homepage1Screen.route) {
                    popUpTo(Screen.OnBoardingScreen.route) { inclusive = true }
                }
            })
        }
        composable(route = Screen.Homepage1Screen.route) {
            Homepage1Screen(
                navigateToHomePage2 = {
                    navController.navigate(Screen.Homepage2Screen.route)
                },
                navigateToHistoryPage = {
                    navController.navigate(Screen.ResultHistoryScreen.route)
                }
            )
        }
        composable(route = Screen.Homepage2Screen.route) {
            Homepage2Screen(
                navigateToHomepage1 = {
                    navController.navigateUp()
                },
                navigateToResult = { heartRateResult ->
                    navController.navigate("${Screen.ResultScreen.route}/$heartRateResult") {
                        popUpTo(Screen.Homepage2Screen.route) { inclusive = true }
                    }
                }
            )
        }
        composable(route = "${Screen.ResultScreen.route}/{heartRateResult}") {
            val heartRateResult = it.arguments!!.getString("heartRateResult")!!.toInt()
            ResultScreen(
                heartRateResult = heartRateResult,
                navigateToHistoryPage = {
                    navController.navigate(Screen.ResultHistoryScreen.route) {
                        popUpTo("${Screen.ResultScreen.route}/{heartRateResult}") { inclusive = true }
                    }
                },
                navigateToHomePage1 = {
                    navController.popBackStack(route = Screen.Homepage1Screen.route, inclusive = false)
                }
            )
        }
        composable(route = Screen.ResultHistoryScreen.route) {
            ResultHistoryScreen(
                navigateToHomePage1 = {
                    navController.popBackStack(route = Screen.Homepage1Screen.route, inclusive = false)
                }
            )
        }
    }
}