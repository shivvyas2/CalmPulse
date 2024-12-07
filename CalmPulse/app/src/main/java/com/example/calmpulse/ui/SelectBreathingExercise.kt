import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Replace these imports with your actual theme
import com.example.calmpulse.ui.theme.LightGreen
import com.example.calmpulse.ui.theme.Typography
import com.example.calmpulse.ui.theme.White

@Composable
fun SelectBreathingExercise() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Select Breathing Mode",
                style = Typography.headlineMedium,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BreathingModeCard(
                    title = "Focus",
                    mode = "Slow",
                    onClick = {}
                )
                BreathingModeCard(
                    title = "Meditate",
                    mode = "Calm",
                    onClick = {}
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BreathingModeCard(
                    title = "Anxiety",
                    mode = "",
                    onClick = {}
                )
                BreathingModeCard(
                    title = "Panic",
                    mode = "",
                    onClick = {}
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /* Start the breathing exercise */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = "Start Now")
            }
        }
    }
}

@Composable
fun BreathingModeCard(
    title: String,
    mode: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = LightGreen),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Updated for Material 3
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = Typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            if (mode.isNotEmpty()) {
                Text(
                    text = mode,
                    style = Typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSelectBreathingExercise() {
    SelectBreathingExercise()
}