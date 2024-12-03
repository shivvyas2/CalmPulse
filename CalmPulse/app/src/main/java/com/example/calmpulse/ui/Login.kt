package com.example.calmpulse.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calmpulse.ui.theme.White

@Composable
fun Login(modifier: Modifier = Modifier){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {

        Text(
            text = "Calm Pulse",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Text(
            text = "Hey, Enter your details to enjoy this beautiful app",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 24.dp)

        )
        Button(onClick = { }) {
            Text(text = "Join Now")
        }
        Button(onClick = { }, ) {
            Text(text = "Create Account",)
        }
    }
}