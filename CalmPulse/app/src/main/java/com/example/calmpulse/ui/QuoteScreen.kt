package com.example.calmpulse.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calmpulse.R
import com.example.calmpulse.api.RetrofitInstance
import com.example.calmpulse.model.Quote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun QuoteScreen(navController: NavController) {
    // State to store the fetched quote
    var quote by remember { mutableStateOf<Quote?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    // Coroutine to fetch the quote from the API
    LaunchedEffect(Unit) {
        try {
            val fetchedQuote = RetrofitInstance.api.getRandomQuote().firstOrNull()
            quote = fetchedQuote
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            isLoading = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDBE681)), // Background color matching the design
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Illustration
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 24.dp), // Spacing below the image
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.meditation), // Replace with your illustration resource
                    contentDescription = "Meditation Illustration",
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Display Quote or Loading State
            if (isLoading) {
                Text(
                    text = "Loading Quote...",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                quote?.let {
                    Text(
                        text = "\"${it.q}\"",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(16.dp),
                        lineHeight = 28.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "- ${it.a}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(16.dp)
                    )
                } ?: run {
                    Text(
                        text = "Failed to fetch quote. Please try again.",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Repeat Exercise Button
            Button(
                onClick = { navController.navigate("SelectBreathingExercise") }, // Navigate to BreathingScreen
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "Repeat Exercise",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Profile Button
            Button(
                onClick = { navController.navigate("ProfileScreen") }, // Navigate to ProfileScreen
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "Profile",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuoteScreenPreview() {
    QuoteScreen(navController = NavController(LocalContext.current))
}
