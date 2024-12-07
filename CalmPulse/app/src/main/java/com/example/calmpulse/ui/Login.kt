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
import com.example.calmpulse.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(modifier: Modifier = Modifier) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    var joinButtonColor by remember { mutableStateOf(Color(0xFFF3F7FA)) }
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
            text = "Calm Pulse",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Text(
            text = "Hey, Enter your details to enjoy this beautiful app",
            fontSize = 12.sp,
            color = Color(0xFFBBC0CC),
            modifier = Modifier.padding(bottom = 24.dp)
        )
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
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
                joinButtonColor = Color(0xFF90EE90)
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
        Button(
            onClick = { 
                createAccountButtonColor = Color(0xFF90EE90)
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