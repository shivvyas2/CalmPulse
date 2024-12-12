package com.example.calmpulse.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calmpulse.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccount(navController: NavController, modifier: Modifier = Modifier) {
    val username = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    var createAccountButtonColor by remember { mutableStateOf(Color(0xFFF3F7FA)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
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
            text = "Create Account",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Text(
            text = "Please enter your details to create an account",
            fontSize = 16.sp,
            color = Color(0xFFBBC0CC),
            modifier = Modifier.padding(bottom = 24.dp)
        )
        TextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text("Username") },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
        )
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
        )
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            modifier = Modifier
                .padding(bottom = 24.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
            visualTransformation = PasswordVisualTransformation()
        )
        Button(
            onClick = {
                createAccountButtonColor = Color(0xFF90EE90)
                navController.navigate("SelectBreathingExercise")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = createAccountButtonColor)
        ) {
            Text(text = "Create Account", color = Color.Black)
        }
    }
}