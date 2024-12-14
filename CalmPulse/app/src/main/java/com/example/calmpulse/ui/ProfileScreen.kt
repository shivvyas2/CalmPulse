package com.example.calmpulse.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calmpulse.R

@Composable
fun ProfileScreen(
    onBreatheClick: () -> Unit
) {
    Scaffold(
        containerColor = Color(0xFFF9F9F9), // Background color for the screen
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 48.dp, start = 24.dp, end = 24.dp), // Screen padding with top spacing
            horizontalAlignment = Alignment.CenterHorizontally // Center the content horizontally
        ) {
            // Greeting Section
            Text(
                text = "Hello there",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF888888),
                modifier = Modifier.align(Alignment.Start) // Align greeting text to the start
            )
            Text(
                text = "Good Evening",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .align(Alignment.Start) // Align greeting text to the start
            )

            // Grid Section
            GridSection(
                items = listOf(
                    GridItem("Notifications", Icons.Default.Notifications, Color(0xFFF3E5F5)),
                    GridItem("Activity", Icons.Default.List, Color(0xFFF5F5F5)),
                    GridItem("Breathe", Icons.Default.CheckCircle, Color(0xFFF5F5F5), onClick = onBreatheClick),
                    GridItem("Sessions", Icons.Default.Star, Color(0xFFF5F5F5)),
                    GridItem("Saved", Icons.Default.Bookmark, Color(0xFFF5F5F5)),
                    GridItem("Explore", Icons.Default.GridView, Color(0xFFF5F5F5))
                )
            )
        }
    }
}

@Composable
fun GridSection(items: List<GridItem>) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val rows = items.chunked(3) // Create rows with 3 items each
        rows.forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                rowItems.forEach { item ->
                    GridItemView(item)
                }
            }
            Spacer(modifier = Modifier.height(24.dp)) // Spacing between rows
        }
    }
}

@Composable
fun GridItemView(item: GridItem) {
    Column(
        modifier = Modifier
            .size(100.dp)
            .clickable { item.onClick?.invoke() }, // Add click listener for items with actions
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(item.backgroundColor, shape = CircleShape), // Circular background
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.label,
                tint = Color.Black,
                modifier = Modifier.size(32.dp) // Icon size
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

// Data class for grid items
data class GridItem(
    val label: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val onClick: (() -> Unit)? = null
)
