package dev.makethiswork.criticaltechnews.core.presentation.designsystem.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.makethiswork.criticaltechnews.core.presentation.designsystem.theme.DsTheme
import dev.makethiswork.criticaltechnews.core.presentation.designsystem.theme.Ink3

@Composable
fun EyebrowText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Ink3,
) {
    Text(
        text = text.uppercase(),
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.labelSmall,
    )
}

@Composable
fun KickerText(text: String, modifier: Modifier = Modifier) {
    EyebrowText(text = text, modifier = modifier, color = DsTheme.flavor.accent)
}
