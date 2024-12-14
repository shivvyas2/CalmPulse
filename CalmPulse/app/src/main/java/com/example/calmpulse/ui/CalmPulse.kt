package com.example.calmpulse.ui

import BreathingExercise
import SelectBreathingExercise
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
fun CalmPulse() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { 
            CommonToolbar(navController, title = "Calm Pulse", toolbarColor = LightGreen) // Set to LightGreen
        }
    ) { paddingValues ->
         NavHost(
             navController = navController,
             startDestination = "FirstWelcomeScreen",
             Modifier.padding(paddingValues) // Apply padding to avoid overlap with the toolbar
         ) {

             composable("FirstWelcomeScreen") {
                 FirstWelcomeScreen(
                     onNavigate = { navController.navigate("SecondWelcomeScreen") }
                 )
             }
             composable("SecondWelcomeScreen") {
                 SecondWelcomeScreen(
                     onNavigate = { navController.navigate("ThirdWelcomeScreen") }
                 )
             }
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
                        navController.navigate("BreathingScreen") // Navigate to BreathingScreen after selecting music
                    }
                )
            }

            // Breathing Screen
            composable("BreathingScreen") {
                BreathingScreen()
            }
            composable("ExploreScreen") {
                ExploreScreen(onBackClick = { navController.popBackStack() })
            }
        }

