package com.example.calmpulse.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun CalmPulse() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "FirstWelcomeScreen"
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
        composable("Login") {
            Login(
                navController = navController
            )
        }
        composable("CreateAccount") {
            CreateAccount(
                navController = navController
            )
        }
        composable("SelectBreathingExercise") {
            SelectBreathingExercise(
                context = LocalContext.current,
                onBackClick = { navController.popBackStack() },
                onConfirmTrack = {
                    navController.navigate("SelectMusic")
                }
            )
        }
        composable("SelectMusic") {
            SelectMusicScreen(
                onBackClick = { navController.popBackStack() },
                onMenuClick = { /* Handle menu click */ },
                onSelectClick = { selectedItem ->
                    // Handle the selected music item
                    navController.popBackStack() // Or navigate to the next screen
                }
            )
        }
    }
}


