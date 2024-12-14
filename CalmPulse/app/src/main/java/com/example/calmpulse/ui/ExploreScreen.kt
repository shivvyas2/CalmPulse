package com.example.calmpulse.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calmpulse.R

data class MeditationItem(
    val title: String,
    val duration: String,
    val category: String,
    val imageRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(onBackClick: () -> Unit) {
    val meditationItems = listOf(
        MeditationItem("10 Minutes Meditation", "27:30", "Mindfulness", R.drawable.calm_image),
        MeditationItem("5 Minutes Guided Meditation", "45:00", "Chill Vibes", R.drawable.calm_image),
        MeditationItem("Feelings Meditations", "17:30", "Gratitude", R.drawable.calm_image),
        MeditationItem("Slumber Practices", "16:30", "Chill Vibes", R.drawable.calm_image)
    )

    var selectedFilter by remember { mutableStateOf("Meditate") }
    val filters = listOf("Focus", "Meditate", "Anxiety")

    Scaffold(
        containerColor = Color(0xFFF9F9F9), // Background color for the screen

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Title
            Text(
                text = "Library",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Filter Row
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                filters.forEach { filter ->
                    FilterChip(
                        label = filter,
                        isSelected = selectedFilter == filter,
                        onClick = { selectedFilter = filter }
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /* Handle sort action */ }) {
                    Icon(imageVector = Icons.Default.Sort, contentDescription = "Sort")
                }
                Button(
                    onClick = { /* Handle filter duration */ },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDCE775)),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
                ) {
                    Text("This Week", fontSize = 14.sp, color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Meditation List
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                meditationItems.forEach { item ->
                    MeditationItemView(item)
                }
            }
        }
    }
}

@Composable
fun FilterChip(label: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFFF3E5F5) else Color.White,
            contentColor = if (isSelected) Color.Black else Color.Gray
        ),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
    ) {
        Text(text = label, fontSize = 14.sp)
    }
}

@Composable
fun MeditationItemView(item: MeditationItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.title,
            modifier = Modifier
                .size(64.dp)
                .background(Color.LightGray, CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "${item.duration} . ${item.category}",
                fontSize = 14.sp,
                color = Color(0xFF888888),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = item.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }
        IconButton(onClick = { /* Handle more actions */ }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More Actions"
            )
        }
    }
}

