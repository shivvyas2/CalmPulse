import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import com.example.calmpulse.model.MusicItem

@Composable
fun MusicItemCard(
    item: MusicItem,             // The music item data
    accentColor: Color,          // The accent color for selection
    isSelected: Boolean,         // Whether this item is selected
    onClick: () -> Unit          // Callback when the item is clicked
) {
    val scale by animateFloatAsState(if (isSelected) 1.1f else 1f)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp) // Add padding around the card
            .clickable { onClick() } // Trigger onClick when tapped
    ) {
        // Album Cover with Selection Highlight
        Box(
            modifier = Modifier
                .size(120.dp)
                .scale(scale) // Add scaling effect
                .clip(CircleShape)
                .background(if (isSelected) accentColor else Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            // Display the album cover image
            Image(
                painter = painterResource(id = item.imageResId), // Load image from drawable resource
                contentDescription = "${item.title} Album Cover",
                modifier = Modifier
                    .size(100.dp) // Adjust size to fit within the circle
                    .clip(CircleShape) // Clip image into a circle
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Music Duration
        Text(
            text = item.duration,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = if (isSelected) accentColor else Color.Gray // Dynamic color
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Music Title
        Text(
            text = item.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (isSelected) accentColor else Color.Black // Dynamic color
        )
    }
}
