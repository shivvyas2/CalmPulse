package com.example.calmpulse.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun BreathingScreen(
    onBackClick: () -> Unit = {}
) {
    // Timer state
    var timeRemaining by remember { mutableStateOf(60) } // Set timer in seconds
    val maxTime = 60 // Maximum time for the timer
    val progress by animateFloatAsState(
        targetValue = timeRemaining.toFloat() / maxTime,
        animationSpec = androidx.compose.animation.core.tween(
            durationMillis = 1000,
            easing = LinearEasing
        ), label = ""
    )

    // Countdown logic
    LaunchedEffect(timeRemaining) {
        if (timeRemaining > 0) {
            delay(1000L)
            timeRemaining -= 1
        }
    }

    Scaffold(

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5)),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Timer Circle
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(200.dp)
            ) {
                // Background circle
                CircularProgressIndicator(
                    progress = progress,
                    color = Color(0xFFD3FFA3), // Lime green
                    strokeWidth = 16.dp,
                    modifier = Modifier.size(200.dp),
                    strokeCap = StrokeCap.Round
                )

                // Center content
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Breathe In",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = String.format("00:%02d", timeRemaining),
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }

            // Audio Playback Controls
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .background(
                        color = Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(24.dp)
                    ),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { /* Pause or Play */ },
                        modifier = Modifier.size(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD3FFA3)),
                        shape = CircleShape
                    ) {
                        Text("II", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Progress visualization
                    Text(
                        text = "| ||||| ||||| ||||| |||||",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Next Exercise Label
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 32.dp)
            ) {
                Text(
                    text = "Next",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "10 Minutes Meditation",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}
