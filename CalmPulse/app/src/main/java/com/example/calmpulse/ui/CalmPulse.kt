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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalmPulse(context: Context) {
    val navController = rememberNavController()

    Scaffold { paddingValues ->
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
                    onNavigate = { navController.navigate("Login") }
                )
            }

            // Login Screen
            composable("Login") {
                Login(navController = navController)
            }

            // Create Account Screen
            composable("CreateAccount") {
                CreateAccount(navController = navController)
            }

            // Profile Screen
            composable("ProfileScreen") {
                ProfileScreen(
                    onBreatheClick = { navController.navigate("SelectBreathingExercise") },
                    onExploreClick = { navController.navigate("ExploreScreen") },
                    onSessionClick = { navController.navigate("SessionScreen") },
                    onNotificationClick = { navController.navigate("NotificationScreen") },
                    onActivityClick = { navController.navigate("ActivityScreen") }

                )
            }

            // Select Breathing Exercise Screen
            composable("SelectBreathingExercise") {
                SelectBreathingExerciseScreen(
                    onBackClick = { navController.popBackStack() },
                    onConfirmTrack = {
                        navController.navigate("SelectMusic")
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
                    onMusicSelected = { musicItem ->
                        navController.navigate("BreathingScreen/${musicItem.audioResId}")
                    }
                )
            }

            // Breathing Screen
            composable("BreathingScreen/{audioResId}") { backStackEntry ->
                val audioResId = backStackEntry.arguments?.getString("audioResId")?.toIntOrNull()
                if (audioResId != null) {
                    BreathingScreen(navController = navController, audioResId = audioResId)
                }
            }
            composable("QuoteScreen") {
                QuoteScreen(navController = navController)
            }


            // Explore Screen
            composable("ExploreScreen") {
                ExploreScreen(onBackClick = { navController.popBackStack() })
            }

            // Session Screen
            composable("SessionScreen") {
                SessionScreen(onBackClick = {  navController.popBackStack()  })
            }

            // Session Screen
            composable("NotificationScreen") {
                NotificationScreen(onBackClick = {  navController.popBackStack()  })
            }
            composable("ActivityScreen") {
                ActivityScreen(onBackClick = { navController.popBackStack() }, onSettingsClick = { navController.navigate("ProfileScreen") })
            }
        }
        }



    }
