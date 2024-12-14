package com.example.calmpulse.ui

import SelectBreathingExerciseScreen
import SelectMusic
import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calmpulse.ui.theme.LightGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalmPulse(context: Context) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            CommonToolbar(
                navController = navController,
                title = "Your App Title",
                toolbarColor = LightGreen
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "FirstWelcomeScreen",
            Modifier.padding(paddingValues)
        ) {

            // First Welcome Screen
            composable("FirstWelcomeScreen") {
                FirstWelcomeScreen(
                    onNavigate = { navController.navigate("SecondWelcomeScreen") }
                )
            }

            // Second Welcome Screen
            composable("SecondWelcomeScreen") {
                SecondWelcomeScreen(
                    onNavigate = { navController.navigate("ThirdWelcomeScreen") }
                )
            }

            // Third Welcome Screen
            composable("ThirdWelcomeScreen") {
                ThirdWelcomeScreen(
                    onNavigate = { navController.navigate("SelectBreathingExercise") }
                )
            }

            // Login Screen
            composable("Login") {
                Login(
                    navController = navController
                )
            }

            // Create Account Screen
            composable("CreateAccount") {
                CreateAccount(
                    navController = navController
                )
            }

            // Select Breathing Exercise Screen
            composable("SelectBreathingExercise") {
                SelectBreathingExerciseScreen(
                    onBackClick = { navController.popBackStack() },
                    onConfirmTrack = { selectedTrack ->
                        // Navigate to the next screen after confirming the track
                        navController.navigate("SelectMusic")
                    },
                    context = context
                )
            }

            // Select Music Screen
            composable("SelectMusic") {
                SelectMusic(
                    onBackClick = { navController.popBackStack() },
                    onMenuClick = {
                        // Handle menu click, if needed
                    }
                )
            }
        }
    }
}
