package com.example.calmpulse.model

import androidx.compose.ui.graphics.vector.ImageVector

// Data class for representing a music item
data class MusicItem(
    val title: String,            // Title of the music track
    val duration: String,         // Duration of the music track
    val icon: ImageVector,        // Icon representing the track
    val audioUrl: String          // URL for the audio file
)