import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SelectBreathingExerciseScreen(
    onBackClick: () -> Unit = {},
    onConfirmTrack: (String) -> Unit = {}, // Callback to handle confirmed breathing mode
    context: Context
) {
    val backgroundColor = Color(0xFFF5F5F5) // Light background
    val accentColor = Color(0xFFDBE681) // Accent color for selection
    val textColor = Color.Black

    val breathingModes = listOf(
        "Focus",
        "Meditate",
        "Slow",
        "Calm",
        "Anxiety",
        "Panic"
    )

    var selectedMode by remember { mutableStateOf("Calm") } // Default selection

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Bar with Back Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = textColor
                    )
                }

                IconButton(onClick = { /* Handle some menu action */ }) {
                    Icon(
                        imageVector = Icons.Default.CheckCircleOutline,
                        contentDescription = "Menu",
                        tint = accentColor
                    )
                }
            }

            // Title
            Text(
                text = "Select Breathing Mode",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Breathing Mode Options
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // First Row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    breathingModes.take(2).forEach { mode ->
                        BreathingModeButton(
                            mode = mode,
                            isSelected = selectedMode == mode,
                            accentColor = accentColor,
                            onClick = { selectedMode = mode }
                        )
                    }
                }

                // Second Row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    breathingModes.subList(2, 4).forEach { mode ->
                        BreathingModeButton(
                            mode = mode,
                            isSelected = selectedMode == mode,
                            accentColor = accentColor,
                            onClick = { selectedMode = mode }
                        )
                    }
                }

                // Third Row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    breathingModes.takeLast(2).forEach { mode ->
                        BreathingModeButton(
                            mode = mode,
                            isSelected = selectedMode == mode,
                            accentColor = accentColor,
                            onClick = { selectedMode = mode }
                        )
                    }
                }
            }

            // "Start Now" Button
            Button(
                onClick = { onConfirmTrack(selectedMode) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = accentColor)
            ) {
                Text(
                    text = "Start Now",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor
                )
            }
        }
    }
}

@Composable
fun BreathingModeButton(
    mode: String,
    isSelected: Boolean,
    accentColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(56.dp),
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) accentColor else Color.White,
            contentColor = if (isSelected) Color.Black else Color.Gray
        ),
        border = if (!isSelected) {
            BorderStroke(2.dp, Color.LightGray)
        } else null
    ) {
        Text(
            text = mode,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
