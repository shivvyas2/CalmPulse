import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SelectMusic(
    onBackClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    val backgroundColor = Color(0xFFF5F5F5)
    val accentColor = Color(0xFFDBE681)
    val textColor = Color.Black
    val categoryList = listOf("All", "Sleep", "Reading", "Calm")
    var selectedCategory by remember { mutableStateOf("All") }

    val musicItems = listOf(
        MusicItem("Ghibli Medley Piano", "30:30", Icons.Default.MusicNote),
        MusicItem("Peace", "30:30", Icons.Default.Eco),
        MusicItem("Conspersa Prometheum", "30:30", Icons.Default.Bolt),
        MusicItem("Night Lofi Playlist", "30:30", Icons.Default.Mic),
    )

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

                    // Menu Button with Accent Outline
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .border(2.dp, accentColor, CircleShape)
                            .clickable { onMenuClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        // Placeholder for a menu icon, can replace with "more_vert"
                        Icon(
                            Icons.Default.Mic,
                            contentDescription = "Menu",
                            tint = textColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                // Title
                Text(
                    text = "Discover Serenity\n& Chill Audio",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Categories
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    categoryList.forEach { category ->
                        CategoryChip(
                            text = category,
                            isSelected = (category == selectedCategory),
                            accentColor = accentColor,
                            textColor = textColor
                        ) {
                            selectedCategory = category
                        }
                    }
                }

                // Music Grid
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(musicItems) { item ->
                        MusicItemCard(item, accentColor)
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryChip(
    text: String,
    isSelected: Boolean,
    accentColor: Color,
    textColor: Color,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(50)
    val containerColor = if (isSelected) accentColor else Color.White
    val borderColor = if (isSelected) Color.Transparent else Color.LightGray

    Box(
        modifier = Modifier
            .height(40.dp)
            .clip(shape)
            .background(containerColor)
            .border(width = 2.dp, color = borderColor, shape = shape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            color = if (isSelected) textColor else Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

data class MusicItem(
    val title: String,
    val duration: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun MusicItemCard(
    item: MusicItem,
    accentColor: Color
) {
    // Placeholder image: just a circle with a background color
    // Adjust size and spacing to match design
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.LightGray),
            contentAlignment = Alignment.BottomStart
        ) {
            // Icon Overlay (Bottom-Left)
            Box(
                modifier = Modifier
                    .offset(x = 8.dp, y = (-8).dp) // Move up inside the circle
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.9f))
                    .border(1.dp, Color.LightGray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    item.icon,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
            }
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


@Preview(showBackground = true)
@Composable
fun PreviewSelectMusic() {
    SelectMusic()
}
