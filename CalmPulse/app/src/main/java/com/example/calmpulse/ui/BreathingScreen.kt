package com.example.calmpulse.ui

import android.graphics.Color.rgb
import android.media.MediaPlayer
import android.os.CountDownTimer
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun BreathingScreen(navController: NavController, audioResId: Int) {
    // State variables
    var isBreatheIn by remember { mutableStateOf(true) }
    var timeLeft by remember { mutableStateOf(4) }
    var totalTimeLeft by remember { mutableStateOf(180) }
    var isPlaying by remember { mutableStateOf(true) }
    var targetProgress by remember { mutableStateOf(if (isBreatheIn) 0f else 1f) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var navigateToQuote by remember { mutableStateOf(false) } // New state variable

    // Navigate to QuoteScreen when the timer ends
    if (navigateToQuote) {
        LaunchedEffect(Unit) {
            navController.navigate("QuoteScreen")
        }
    }

    // MediaPlayer setup
    DisposableEffect(audioResId) {
        mediaPlayer = MediaPlayer.create(navController.context, audioResId).apply {
            isLooping = true
            start()
        }
        onDispose {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    // Progress animations
    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = tween(
            durationMillis = 4000,
            easing = LinearOutSlowInEasing
        )
    )

    LaunchedEffect(isBreatheIn) {
        targetProgress = if (isBreatheIn) 1f else 0f
    }

    // Timers
    val breathTimer = remember {
        object : CountDownTimer(4000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = (millisUntilFinished / 1000).toInt() + 1
            }

            override fun onFinish() {
                isBreatheIn = !isBreatheIn
                timeLeft = 4
                start()
            }
        }
    }

    val overallTimer = remember {
        object : CountDownTimer(180000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                totalTimeLeft = (millisUntilFinished / 1000).toInt()
            }

            override fun onFinish() {
                totalTimeLeft = 0
                navigateToQuote = true // Set navigation flag when timer ends
            }
        }
    }

    DisposableEffect(Unit) {
        breathTimer.start()
        overallTimer.start()
        onDispose {
            breathTimer.cancel()
            overallTimer.cancel()
        }
    }

    // UI layout
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Title
            Text(
                text = if (isBreatheIn) "Breathe In" else "Breathe Out",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Timer
            Text(
                text = String.format("00:%02d", timeLeft),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Circular Progress Indicator (Larger Circle)
            Box(
                modifier = Modifier
                    .size(300.dp) // Increase the size of the circle
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = animatedProgress,
                    color = if (isBreatheIn) Color(0xFFB0E57C) else Color(0xFFFF8A80),
                    strokeWidth = 16.dp, // Slightly thicker stroke
                    modifier = Modifier.size(280.dp) // Adjust the progress indicator size
                )
                Icon(
                    imageVector = Icons.Default.Bolt,
                    contentDescription = null,
                    tint = Color(rgb(245, 211, 227)),
                    modifier = Modifier.size(80.dp) // Increase the size of the icon
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Total Time Remaining
            Text(
                text = "Time Remaining: ${totalTimeLeft / 60}:${String.format("%02d", totalTimeLeft % 60)}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Playback controls with waveform
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFF0F0F0),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    if (isPlaying) {
                        mediaPlayer?.pause()
                    } else {
                        mediaPlayer?.start()
                    }
                    isPlaying = !isPlaying
                }) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(Color(0xFFDBE681), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Waveform visualization (static representation)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFEAEAEA)),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        repeat(20) { index ->
                            Box(
                                modifier = Modifier
                                    .width(4.dp)
                                    .height((1..30).random().dp) // Randomize bar heights
                                    .background(
                                        if (index % 2 == 0) Color(0xFF000000) else Color(0xFFB0B0B0)
                                    )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // "Need Inspiration?" Button
            Button(
                onClick = { navController.navigate("QuoteScreen") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDBE681)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Need Inspiration?",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // "Go to Profile" Button
            Button(
                onClick = { navController.navigate("ProfileScreen") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDBE681)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Go to Profile",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

