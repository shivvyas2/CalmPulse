package com.example.calmpulse.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.calmpulse.R
import com.example.calmpulse.ui.theme.LightGreen

@Composable
fun ThirdWelcomeScreen(modifier: Modifier = Modifier, onNavigate: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(LightGreen),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_3),
            contentDescription = "Welcome Image 3",
            modifier = Modifier
                .width(250.dp)
                .height(180.dp)
                .padding(bottom = 24.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.stepper_3),
            contentDescription = "Stepper Image 3",
            modifier = Modifier
                .width(100.dp)
                .height(80.dp)
                .padding(bottom = 24.dp)
        )
        Text(
            text = "Health is Wealth",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Button(
            onClick = { onNavigate() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Text(text = "Next", color = Color.Black)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewThirdWelcomeScreen() {
    ThirdWelcomeScreen(onNavigate = {})
}
