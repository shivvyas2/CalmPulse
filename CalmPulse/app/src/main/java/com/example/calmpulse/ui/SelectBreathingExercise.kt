import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
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
import androidx.navigation.NavHostController

@Composable
fun SelectBreathingExercise(
    onBackClick: () -> Unit = {},
    onStartClick: () -> Unit = {},
    onNavigate: () -> Unit = {},
    navController: NavHostController
) {
    val backgroundColor = Color(0xFFF5F5F5)
    val accentColor = Color(0xFFDBE681)
    val textColor = Color.Black

    var selectedModes by remember { mutableStateOf(setOf<String>()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                // Top Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Back Button (Circle)
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = textColor)
                    }

                    // Clipboard Icon with Accent Outline
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(50))
                            .border(2.dp, accentColor, RoundedCornerShape(50))
                            .clickable { /* Handle clipboard click */ },
                        contentAlignment = Alignment.Center
                    ) {
                        // Use a generic icon or another icon available
                        Icon(
                            Icons.Default.CheckCircle,
                            contentDescription = "Clipboard",
                            tint = textColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                // Title
                Text(
                    text = "Select Breathing Mode",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    modifier = Modifier.padding(bottom = 48.dp)
                )

                // Breathing Modes Layout
                // You can dynamically generate these if needed
                val modes = listOf(
                    "Focus" to "Slow",
                    "Meditate" to "Calm",
                    "Anxiety" to "Panic"
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    modes.forEach { (leftMode, rightMode) ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            BreathingModeItem(
                                title = leftMode,
                                isSelected = selectedModes.contains(leftMode),
                                onClick = {
                                    selectedModes = if (selectedModes.contains(leftMode)) {
                                        selectedModes - leftMode
                                    } else {
                                        selectedModes + leftMode
                                    }
                                },
                                accentColor = accentColor,
                                textColor = textColor
                            )

                            BreathingModeItem(
                                title = rightMode,
                                isSelected = selectedModes.contains(rightMode),
                                onClick = {
                                    selectedModes = if (selectedModes.contains(rightMode)) {
                                        selectedModes - rightMode
                                    } else {
                                        selectedModes + rightMode
                                    }
                                },
                                accentColor = accentColor,
                                textColor = textColor
                            )
                        }
                    }
                }

                // Start Button
                Button(
                    onClick = onStartClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(top = 24.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = accentColor)
                ) {
                    Text(
                        "Start Now",
                        color = textColor,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun BreathingModeItem(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    accentColor: Color,
    textColor: Color
) {
    val shape = RoundedCornerShape(50)

    val containerColor = if (isSelected) accentColor else Color.White
    val borderColor = if (isSelected) Color.Transparent else Color.LightGray

    Box(
        modifier = Modifier
            .width(140.dp)
            .height(48.dp)
            .clip(shape)
            .border(width = 2.dp, color = borderColor, shape = shape)
            .background(containerColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (isSelected) {
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = textColor,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = textColor
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewSelectBreathingExercise() {
//    SelectBreathingExercise(navController = navController)
//}
