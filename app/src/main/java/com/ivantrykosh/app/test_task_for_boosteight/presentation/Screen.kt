package com.ivantrykosh.app.test_task_for_boosteight.presentation

/**
 * Contains all screens in app
 */
sealed class Screen(val route: String) {
    data object LoadingScreen: Screen("loading_screen")
    data object OnBoardingScreen: Screen("onboarding_screen")
    data object Homepage1Screen: Screen("homepage1_screen")
    data object Homepage2Screen: Screen("homepage2_screen")
    data object ResultScreen: Screen("result_screen")
    data object ResultHistoryScreen: Screen("result_history_screen")
}