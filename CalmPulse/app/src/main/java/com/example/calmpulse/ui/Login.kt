package com.example.calmpulse.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calmpulse.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavController) {
    // State variables for email and password
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    // State variables for button colors
    var joinButtonColor by remember { mutableStateOf(Color(0xFFF3F7FA)) }

    Scaffold(
        topBar = { 
            CommonToolbar(navController, title = "Login", toolbarColor = Color.White) // Set to White
        }
    ) { paddingValues ->
        // Main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Apply padding to avoid overlap with the toolbar
                .background(Color.White), // Set background color to white
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_mark),
                contentDescription = "Logo Mark",
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .size(150.dp)
            )
            Text(
                text = "Calm Pulse",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Text(
                text = "Hey, Enter your details to enjoy this beautiful app",
                fontSize = 16.sp,
                color = Color(0xFFBBC0CC),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Email input field
            TextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") },
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .background(Color.White),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
            )

            // Password input field
            TextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") },
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .background(Color.White),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                visualTransformation = PasswordVisualTransformation()
            )

            // Button to join
            Button(
                onClick = { 
                    joinButtonColor = Color(0xFF90EE90) 
                    navController.navigate("SelectBreathingExercise") // Navigate to Select Breathing Exercise
                },
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = joinButtonColor)
            ) {
                Text(text = "Join Now", color = Color.Black)
            }

            // Button to navigate to Create Account screen
            Button(
                onClick = { navController.navigate("CreateAccount") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF3F7FA))
            ) {
                Text(text = "Create Account", color = Color.Black)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewLogin() {
//    Login()
//}

