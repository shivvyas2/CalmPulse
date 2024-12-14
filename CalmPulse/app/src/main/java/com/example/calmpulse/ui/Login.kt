package com.example.calmpulse.ui

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calmpulse.R
import com.google.firebase.auth.FirebaseAuth

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

    // Firebase Authentication instance
    val firebaseAuth = FirebaseAuth.getInstance()
    val context = LocalContext.current

    // To handle soft keyboard interactions
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        containerColor = Color(0xFFF5F5F5), // Set background color for the entire screen
    ) { paddingValues ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
                .imePadding(), // Adjust padding when the keyboard is visible
        ) {
            val screenHeight = with(LocalDensity.current) { constraints.maxHeight.toDp() }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .background(Color.White)
                    .heightIn(max = screenHeight),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Logo
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .size(100.dp)
                )

                // Title
                Text(
                    text = "Calm Pulse",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5E503F), // Brown color for the title
                    modifier = Modifier.padding(bottom = 16.dp)
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
                        .padding(vertical = 8.dp)
                        .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(12.dp)),
                    isError = !isEmailValid.value,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedTextColor = Color.Black, // Text color when focused
                        unfocusedTextColor = Color.Black, // Text color when unfocused
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
                    placeholder = { Text("Password", color = Color(0xFFBBC0CC)) },
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
                        .padding(vertical = 8.dp)
                        .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(12.dp)),
                    isError = !isPasswordValid.value,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedTextColor = Color.Black, // Text color when focused
                        unfocusedTextColor = Color.Black, // Text color when unfocused
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
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                // Join Now Button
                Button(
                    onClick = {
                        keyboardController?.hide() // Hide keyboard on button press
                        if (isEmailValid.value && isPasswordValid.value) {
                            firebaseAuth.signInWithEmailAndPassword(
                                email.value.trim(),
                                password.value.trim()
                            ).addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT)
                                        .show()
                                    navController.navigate("ProfileScreen")
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Login failed: ${task.exception?.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        } else {
                            showErrorMessage = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD3FFA3)) // Light green
                ) {
                    Text(
                        text = "Breathe",
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
                        .padding(vertical = 8.dp)
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
}
