package com.example.calmpulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.example.calmpulse.ui.CalmPulse

import com.example.calmpulse.ui.theme.CalmPulseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalmPulseTheme {
                CalmPulse(this)
            }
        }
    }
}