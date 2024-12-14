package com.example.calmpulse.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.calmpulse.ui.theme.LightGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccount(navController: NavController) {
    // State variables for email and password
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    var createAccountButtonColor by remember { mutableStateOf(Color(0xFFF3F7FA)) }

    // Scaffold to include the toolbar
    Scaffold(
        topBar = { 
            CommonToolbar(navController, title = "Create Account", toolbarColor = Color.White) // Set toolbar color to white
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
                text = "Hey, Enter your details to enjoy this beautiful app",
                fontSize = 12.sp,
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

            // Button to create account
            Button(
                onClick = {
                    // Handle account creation logic here
                },
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