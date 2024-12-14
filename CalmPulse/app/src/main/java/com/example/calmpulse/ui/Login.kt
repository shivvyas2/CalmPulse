package com.example.calmpulse.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.calmpulse.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    navController: NavController
) {
    // State variables for email and password
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val isEmailValid = remember { mutableStateOf(true) }
    val isPasswordValid = remember { mutableStateOf(true) }
    var showErrorMessage by remember { mutableStateOf(false) }

    // To handle soft keyboard interactions
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        containerColor = Color(0xFFF5F5F5), // Set background color for the entire screen
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
                .imePadding(), // Adjust padding when the keyboard is visible
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo_mark),
                contentDescription = "Logo Mark",
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .size(120.dp)
            )

            // Title
            Text(
                text = "Calm Pulse",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5E503F), // Brown color for the title
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Subtitle
            Text(
                text = "Hey, Enter your details to enjoy this beautiful app",
                fontSize = 14.sp,
                color = Color(0xFFBBC0CC),
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Email Input Field
            TextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                    isEmailValid.value = android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()
                },
                placeholder = { Text("Email", color = Color(0xFFBBC0CC)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon",
                        tint = Color(0xFFBBC0CC)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(12.dp)),
                isError = !isEmailValid.value,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            // Password Input Field
            TextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                    isPasswordValid.value = it.length >= 6
                },
                placeholder = { Text("Passcode", color = Color(0xFFBBC0CC)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password Icon",
                        tint = Color(0xFFBBC0CC)
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(12.dp)),
                isError = !isPasswordValid.value,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            // Validation Error Message
            if (showErrorMessage) {
                Text(
                    text = "Please correct the errors above",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
                )
            }

            // Join Now Button
            Button(
                onClick = {
                    keyboardController?.hide() // Hide keyboard on button press
                    if (isEmailValid.value && isPasswordValid.value) {
                        navController.navigate("SelectBreathingExercise")
                    } else {
                        showErrorMessage = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD3FFA3)) // Light green
            ) {
                Text(
                    text = "Join Now",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }

            // Create Account Button
            Button(
                onClick = {
                    keyboardController?.hide() // Hide keyboard on button press
                    navController.navigate("CreateAccount")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF3F7FA)) // Light gray
            ) {
                Text(
                    text = "Create Account",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    }
}
