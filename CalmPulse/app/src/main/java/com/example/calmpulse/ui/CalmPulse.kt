package com.example.calmpulse.ui

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
            CommonToolbar(navController, title = "Your App Title", toolbarColor = LightGreen) // Set to LightGreen
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
                 ThirdWelcomeScreen(
                     onNavigate = { navController.navigate("SelectBreathingExercise") }
                 )
             }
         }
     }
}

