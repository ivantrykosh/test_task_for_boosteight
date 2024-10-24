package com.ivantrykosh.app.test_task_for_boosteight.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.TestTaskForBoosteightTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            TestTaskForBoosteightTheme {
                NavGraph(navController = navController)
            }
        }
    }
}