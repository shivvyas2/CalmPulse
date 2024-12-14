package com.example.calmpulse.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.calmpulse.R
import com.example.calmpulse.ui.theme.LightGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonToolbar(navController: NavController, title: String, toolbarColor: Color) {
    Column(
        modifier = Modifier.background(Color(0xFF6200EE)) // Set the background color for the column
    ) {
        TopAppBar(
            title = { Text(title) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Image(
                        painter = painterResource(id = R.drawable.back_icon), // Use your back icon resource
                        contentDescription = "Back",
                        modifier = Modifier.size(24.dp) // Adjust size as needed
                    )
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = toolbarColor) // Set the toolbar color
        )
    }
} 