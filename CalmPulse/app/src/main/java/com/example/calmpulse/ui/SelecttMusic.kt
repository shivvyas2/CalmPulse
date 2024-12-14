import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calmpulse.R

// Updated MusicItem class to include raw resource ID
data class MusicItem(
    val title: String,
    val duration: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val audioResId: Int // Raw resource ID for the audio
)

@Composable
fun SelectMusic(
    context: Context,
    onBackClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onMusicSelected: (MusicItem) -> Unit = {} // Callback to send selected music to the next screen
) {
    val backgroundColor = Color(0xFFF5F5F5)
    val accentColor = Color(0xFFDBE681)
    val textColor = Color.Black

    // Hardcoded music items with raw resources
    val musicItems = listOf(
        com.example.calmpulse.model.MusicItem(
            "Calm Audio",
            "2:00",
            Icons.Default.MusicNote,
            R.raw.calm_audio,
            R.drawable.calm_image
        ),
        com.example.calmpulse.model.MusicItem(
            "Focus Audio",
            "3:00",
            Icons.Default.MusicNote,
            R.raw.focus_audio,
            R.drawable.focus_image
        ),
        com.example.calmpulse.model.MusicItem(
            "Meditate Audio",
            "2:00",
            Icons.Default.MusicNote,
            R.raw.meditate_audio,
            R.drawable.meditate_image
        ),
        com.example.calmpulse.model.MusicItem(
            "Panic Audio",
            "2:30",
            Icons.Default.MusicNote,
            R.raw.panic_audio,
            R.drawable.panic_image
        )
    )

    var previewingMusic by remember { mutableStateOf<MusicItem?>(null) }
    var selectedMusic by remember { mutableStateOf<MusicItem?>(null) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var isPlayingPreview by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        // Clean up MediaPlayer on exit
        onDispose {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Card(
                modifier = Modifier
                    .weight(1f)
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
                        IconButton(onClick = onBackClick) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = textColor)
                        }

                        IconButton(onClick = onMenuClick) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = textColor)
                        }
                    }

                    // Title
                    Text(
                        text = "Discover Serenity & Chill Audio",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )

                    // Music Grid
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        items(musicItems) { item ->
                            MusicItemCard(item, accentColor, previewingMusic == item) {
                                if (isPlayingPreview && previewingMusic == item) {
                                    // Stop playback if the same music is tapped again
                                    mediaPlayer?.stop()
                                    mediaPlayer?.release()
                                    mediaPlayer = null
                                    isPlayingPreview = false
                                    previewingMusic = null
                                } else {
                                    // Play the preview of the selected music
                                    mediaPlayer?.stop()
                                    mediaPlayer?.release()
                                    previewingMusic = item
                                    mediaPlayer = MediaPlayer.create(context, item.audioResId).apply {
                                        start()
                                        isPlayingPreview = true
                                        setOnCompletionListener {
                                            isPlayingPreview = false
                                            previewingMusic = null
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Select & Confirm Button
            Button(
                onClick = {
                    if (previewingMusic != null) {
                        selectedMusic = previewingMusic
                        onMusicSelected(selectedMusic!!)
                    }
                },
                enabled = previewingMusic != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (previewingMusic != null) accentColor else Color.Gray,
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = if (previewingMusic != null) "Confirm Selection" else "Select Music",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun MusicItemCard(
    item: MusicItem,
    accentColor: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(if (isSelected) accentColor else Color.LightGray),
            contentAlignment = Alignment.BottomStart
        ) {
            Icon(
                item.icon,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Duration
        Text(
            text = item.duration,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Title
        Text(
            text = item.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
    }
}
