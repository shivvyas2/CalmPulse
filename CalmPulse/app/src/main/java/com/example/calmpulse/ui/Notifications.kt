package com.example.calmpulse.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(onBackClick: () -> Unit) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val horizontalPadding = if (screenWidth < 600.dp) 16.dp else 32.dp

    Scaffold(
        containerColor = Color(0xFFF8F5F2), // Background color matching the design
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Notifications",
                        fontWeight = FontWeight.Bold,
                        fontSize = if (screenWidth < 600.dp) 20.sp else 24.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(end = horizontalPadding / 2)
                            .clip(CircleShape)
                            .background(Color(0xFFFFEDED)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "+11",
                            color = Color(0xFFEB5757),
                            fontSize = if (screenWidth < 600.dp) 12.sp else 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = horizontalPadding)
        ) {
            NotificationSection(
                title = "Earlier This Day",
                notifications = listOf(
                    NotificationItem(
                        title = "Message from Dr Freud AI!",
                        description = "52 Total Unread Messages ⚠️",
                        iconColor = Color(0xFF6CD4FF),
                        isCompleted = false
                    ),
                    NotificationItem(
                        title = "Journal Incomplete!",
                        description = "It’s Reflection Time! ✍️",
                        iconColor = Color(0xFF9B87FF),
                        progress = 60
                    ),
                    NotificationItem(
                        title = "Exercise Complete!",
                        description = "22m Breathing Done. 🧘‍♂️",
                        iconColor = Color(0xFFA4736E),
                        isCompleted = true
                    ),
                    NotificationItem(
                        title = "Mental Health Data is Here.",
                        description = "Your Monthly Mental Analysis is here",
                        iconColor = Color(0xFFFFD17A),
                        buttonLabel = "Shiv's Health Data.pdf"
                    ),
                    NotificationItem(
                        title = "Mood Improved.",
                        description = "Neutral → Happy",
                        iconColor = Color(0xFF82C755),
                        isCompleted = true
                    )
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            NotificationSection(
                title = "Last Week",
                notifications = listOf(
                    NotificationItem(
                        title = "Stress Decreased.",
                        description = "Stress Level is now 3.",
                        iconColor = Color(0xFFFF8A80),
                        progress = 40
                    ),
                    NotificationItem(
                        title = "Dr Freud Recommendations.",
                        description = "48 Health Recommendations",
                        iconColor = Color(0xFFA4736E)
                    )
                )
            )
        }
    }
}

@Composable
fun NotificationSection(title: String, notifications: List<NotificationItem>) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val textSize = if (screenWidth < 600.dp) 16.sp else 18.sp // Adjust text size dynamically

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            fontSize = textSize,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6C5B4C),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        notifications.forEach { notification ->
            NotificationCard(notification)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun NotificationCard(notification: NotificationItem) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardPadding = if (screenWidth < 600.dp) 16.dp else 24.dp // Adjust padding for cards dynamically

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(cardPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon Circle
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(notification.iconColor),
                contentAlignment = Alignment.Center
            ) {
                if (notification.isCompleted) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Completed",
                        tint = Color.White
                    )
                } else {
                    Text(
                        text = notification.iconText,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Notification Details
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = notification.title,
                    fontSize = if (screenWidth < 600.dp) 14.sp else 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF403A36)
                )
                Text(
                    text = notification.description,
                    fontSize = if (screenWidth < 600.dp) 12.sp else 14.sp,
                    color = Color(0xFF6C5B4C)
                )
            }

            // Progress or Action
            notification.progress?.let { progress ->
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFEEEDED)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$progress%",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6C5B4C)
                    )
                }
            }
            notification.buttonLabel?.let { label ->
                Button(
                    onClick = { /* Handle button click */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFAFAFA)),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                ) {
                    Text(
                        text = label,
                        fontSize = 12.sp,
                        color = Color(0xFF6C5B4C)
                    )
                }
            }
        }
    }
}

// NotificationItem data class
data class NotificationItem(
    val title: String,
    val description: String,
    val iconColor: Color,
    val iconText: String = "",
    val isCompleted: Boolean = false,
    val progress: Int? = null,
    val buttonLabel: String? = null
)
