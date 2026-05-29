package dev.makethiswork.criticaltechnews.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import dev.makethiswork.criticaltechnews.core.presentation.designsystem.theme.DsIcons

@Composable
fun PhotoPlaceholder(modifier: Modifier = Modifier) {
    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFFE9E5DC), Color(0xFFD7D1C4)),
        start = Offset(0f, 0f),
        end = Offset.Infinite,
    )
    Box(
        modifier = modifier.background(gradient),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = DsIcons.Photo,
            contentDescription = null,
            tint = Color.Black.copy(alpha = 0.20f),
            modifier = Modifier.fillMaxSize(0.30f),
        )
    }
}
