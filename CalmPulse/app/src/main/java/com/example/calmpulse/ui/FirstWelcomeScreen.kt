package com.example.calmpulse.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calmpulse.ui.theme.CalmPulseTheme
import com.example.calmpulse.ui.theme.LightGreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import com.example.calmpulse.R

@Composable
fun FirstWelcomeScreen(onNavigate: () -> Unit, showToolbar: Boolean = true){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .background(LightGreen),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

        ) {
            Image(
                painter = painterResource(id = R.drawable.image_1),
                contentDescription = "Welcome Image",
                modifier = Modifier
                    .width(250.dp)
                    .height(180.dp)
                    .padding(bottom = 24.dp),
                contentScale = ContentScale.FillWidth
            )
            Image(
                painter = painterResource(id = R.drawable.stepper_1),
                contentDescription = "Stepper Image",
                modifier = Modifier
                    .width(100.dp)
                    .height(80.dp)
                    .padding(bottom = 24.dp),
                contentScale = ContentScale.FillWidth
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
fun PreviewWelcomeScreen() {
     CalmPulseTheme {
        FirstWelcomeScreen(onNavigate = {} )
    }

}