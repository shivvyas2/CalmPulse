package com.example.calmpulse.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun CalmPulse(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "FirstWelcomeScreen"
    ){
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
        composable("ThirdWelcomeScreen"){
            ThirdWelcomeScreen(
                onNavigate = {navController.navigate("Login")}
            )
        }
        composable("Login"){
            Login()
        }
    }

}