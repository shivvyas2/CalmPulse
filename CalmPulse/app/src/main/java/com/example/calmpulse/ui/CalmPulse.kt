package com.example.calmpulse.ui

import BreathingExercise
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalmPulse(context: Context) {
    val navController = rememberNavController()

    Scaffold() { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "FirstWelcomeScreen", // Starting point
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
                    onNavigate = { navController.navigate("Login") }
                )
            }

            // Login Screen
            composable("Login") {
                Login(
                    navController = navController
                )
            }

            // Create Account (SignUp) Screen
            composable("CreateAccount") {
                CreateAccount(
                    navController = navController
                )
            }

            // Profile Screen
            composable("ProfileScreen") {
                ProfileScreen(
                    onBreatheClick = {
                        navController.navigate("SelectBreathingExercise")
                    }
                    ,
                    onExploreClick = {
                        navController.navigate("ExploreScreen")
                    }
                )
            }

            // Select Breathing Exercise Screen
            composable("SelectBreathingExercise") {
                SelectBreathingExerciseScreen(
                    onBackClick = { navController.popBackStack() },
                    onConfirmTrack = {
                        navController.navigate("SelectMusic") // Navigate to SelectMusic after selecting track
                    },
                    context = context
                )
            }

            // Select Music Screen
            composable("SelectMusic") {
                SelectMusic(
                    context = context,
                    onBackClick = { navController.popBackStack() },
                    onMenuClick = { /* Handle menu actions */ },
                    onMusicSelected = {
                        navController.navigate("BreathingExercise") // Navigate to BreathingScreen after selecting music
                    }
                )
            }

            // Breathing Screen
            composable("BreathingScreen") {
                BreathingScreen(navController = navController)
            }
            composable("ExploreScreen") {
                ExploreScreen(onBackClick = { navController.popBackStack() })
            }
            composable("BreathingExercise") {
                BreathingExercise(navController = navController)
            }
        }

    }
}
