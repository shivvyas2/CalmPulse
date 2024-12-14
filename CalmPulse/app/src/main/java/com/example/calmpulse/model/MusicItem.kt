package com.example.calmpulse.model

import androidx.compose.ui.graphics.vector.ImageVector

data class MusicItem(
    val title: String,
    val duration: String,

    val audioResId: Int, // Raw resource ID for the audio
    val imageResId: Int // Drawable resource ID for the image
)
