package com.example.calmpulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calmpulse.ui.FirstWelcomeScreen
import com.example.calmpulse.ui.SecondWelcomeScreen
import com.example.calmpulse.ui.CalmPulse
import com.example.calmpulse.ui.ThirdWelcomeScreen
import com.example.calmpulse.ui.theme.CalmPulseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalmPulseTheme {
                CalmPulse()
            }
        }
    }
}