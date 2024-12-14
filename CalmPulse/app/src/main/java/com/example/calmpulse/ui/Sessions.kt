package com.example.calmpulse.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calmpulse.R

@Composable
fun SessionScreen(onBackClick: () -> Boolean) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F6F4)) // Background color
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Top Bar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { /* Handle back navigation */ }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFF6D4C41) // Brown color
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Session History",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6D4C41) // Brown color
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Days Selector
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
                days.forEachIndexed { index, day ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clickable { /* Handle day click */ }
                            .padding(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(
                                    if (index == 0) Color(0xFFD7CCC8) // Highlight first day
                                    else Color(0xFFECEFF1) // Default gray
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = (25 + index).toString(),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (index == 0) Color(0xFF6D4C41) else Color.Gray
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = day,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // All Sessions Header
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "All Sessions (25)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6D4C41)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Newest First",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.FilterList,
                        contentDescription = "Filter",
                        tint = Color(0xFF6D4C41) // Brown color
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Session Cards
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                // Dummy data for session cards
                val sessions = listOf(
                    Triple("July 25, 2027", "Completed", 25),
                    Triple("July 26, 2027", "Incomplete", 18),
                    Triple("July 25, 2027", "Incomplete", 187),
                    Triple("July 24, 2027", "Completed", 664)
                )
                sessions.forEach { (date, status, minutes) ->
                    SessionCard(date, status, minutes)
                }
            }
        }
    }
}

@Composable
fun SessionCard(date: String, status: String, minutes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.weight(1f)
        ) {
            // Date and Status
            Text(
                text = date,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6D4C41)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "10:25 - 11:30 AM · $status",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            // Audio Info
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.sound),
                    contentDescription = "Audio Icon",
                    tint = Color(0xFF6D4C41),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Chirping Birds",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    painter = painterResource(id = R.drawable.chart), // Replace with your icon
                    contentDescription = "Speed Icon",
                    tint = Color(0xFF6D4C41), // Brown color
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Slow",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            }
        }

        // Session Duration
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color(0xFFDCEDC8)), // Light green background
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$minutes min",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6D4C41) // Brown color
            )
        }
    }
}
