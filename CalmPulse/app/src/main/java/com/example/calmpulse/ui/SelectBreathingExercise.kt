import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import com.example.calmpulse.ui.theme.White

@Composable
fun SelectBreathingExercise01Screen() {
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
            .clickable { onClick() },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = LightGreen
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
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