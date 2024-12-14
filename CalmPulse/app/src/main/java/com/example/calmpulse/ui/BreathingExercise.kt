import android.graphics.Color.rgb
import android.os.CountDownTimer
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun BreathingExercise(navController: NavController) {
    // State variables
    var isBreatheIn by remember { mutableStateOf(true) }


    //Times for inhale and exhale/ Overall exercise remaining in seconds
    var timeLeft by remember { mutableStateOf(4) } //
    var totalTimeLeft by remember { mutableStateOf(180) } //


    // Progress indicators
    var exerciseProgress by remember { mutableStateOf(1f) }

    //for audio playback
    var isPlaying by remember { mutableStateOf(true) }
    var targetProgress by remember { mutableStateOf(if (isBreatheIn) 0f else 1f) }

    // Start the animation when the composable is first launched.
    // We use LaunchedEffect to trigger the animation only once at the beginning.
    LaunchedEffect(isBreatheIn) {
        targetProgress = if (isBreatheIn) 1f else 0f
    }

    // Smooth animation for the progress indicator
    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = tween(
            // set to 4 seconds for both inhale and exhale to match the timer
            durationMillis = 4000,
            easing = LinearOutSlowInEasing
        )
    )


    /////////////////////////// Timer for inhale/exhale /////////////////////////////////////////
    val breathTimer = remember {
        object : CountDownTimer(4000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = (millisUntilFinished / 1000).toInt() + 1
            }

            override fun onFinish() {
                // Toggle between inhale and exhale
                isBreatheIn = !isBreatheIn
                // Reset time for next cycle (4 seconds)
                timeLeft = 4
                // Restart the breath timer for the next cycle
                start()
            }
        }
    }

    ///////////////////////////////////// Timer for the overall exercise /////////////////////////////////////////
    val overallTimer = remember {
        object : CountDownTimer(180000, 1000) { // 3 minutes total
            override fun onTick(millisUntilFinished: Long) {
                totalTimeLeft = (millisUntilFinished / 1000).toInt()
                exerciseProgress = millisUntilFinished / 180000f
            }

            override fun onFinish() {
                totalTimeLeft = 0
                exerciseProgress = 0f
                navController.navigate("PositiveQuote") // Navigate to PositiveQuote page
            }
        }
    }

    // Start the timers when the composable is first launched and cancel them when the composable is removed from the screen
    DisposableEffect(Unit) {
        breathTimer.start()
        overallTimer.start()
        onDispose {
            breathTimer.cancel()
            overallTimer.cancel()
        }
    }

//////////////////////////////////////Start of the UI Layout//////////////////////////////////////
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            ////////////////////// Title for either "Breathe In" or "Breathe Out"//////////////////////
            Text(
                text = if (isBreatheIn) "Breathe In" else "Breathe Out",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            //////////////////////Timer Countdown for inhale/exhale//////////////////////
            Text(
                text = "00:0${timeLeft}",
                fontSize = 25.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            /////////////////// Circular progress indicator and Icon in the middle //////////////////////////////////////
            Box(
                modifier = Modifier.size(200.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = animatedProgress,
                    // Green for inhale, red for exhale
                    color = if (isBreatheIn) Color(0xFFB0E57C) else Color(0xFFFF8A80),
                    strokeWidth = 12.dp,
                    modifier = Modifier.size(200.dp)
                )
                Icon(
                    imageVector = Icons.Default.Bolt,
                    contentDescription = null,
                    tint = Color(rgb(245,211,227)),
                    modifier = Modifier.size(70.dp)
                )
            }




            Spacer(modifier = Modifier.height(24.dp))



            /////////////////////////// Total exercise time left with its progress bar///////////////////////////
            Text(
                text = "Time Remaining: ${totalTimeLeft / 60}:${String.format("%02d", totalTimeLeft % 60)}",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            LinearProgressIndicator(
                progress = exerciseProgress,
                color = Color(rgb(92,100,249)),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))


            ///////////////////////////// Playback controls for audio//////////////////////////////////
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .background(
                        color = Color(0xFFF0F0F0),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { isPlaying = !isPlaying }) {
                    Box(
                        modifier = Modifier
                            .size(65.dp)
                            .background(
                                color = Color(0xFF000000),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))



               //////////////////// // Visualization bar for audio source////////////////////////////

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(2.dp))
                )
            }


            Spacer(modifier = Modifier.height(24.dp))




            /////////////////////////// Complete Workout Button//////////////////////////////////
            Button(
                onClick = { navController.navigate("PositiveQuote") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF000000))
            ) {
                Text(
                    text = "Complete Workout",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
