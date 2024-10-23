package com.ivantrykosh.app.test_task_for_boosteight.presentation

sealed class Screen(val route: String) {
    data object LoadingScreen: Screen("loading_screen")
    data object OnBoardingScreen: Screen("onboarding_screen")
    data object Homepage1Screen: Screen("homepage1_screen")
    data object GreetingScreen: Screen("greeting_screen")
}