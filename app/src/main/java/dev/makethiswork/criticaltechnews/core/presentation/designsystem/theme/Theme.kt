package dev.makethiswork.criticaltechnews.core.presentation.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun CriticalTechNewsTheme(
    flavorPalette: FlavorPalette = rememberFlavorPalette(),
    content: @Composable () -> Unit,
) {
    val colorScheme = lightColorScheme(
        primary = flavorPalette.accent,
        onPrimary = Surface,
        secondary = Ink2,
        onSecondary = Surface,
        background = Paper,
        onBackground = Ink,
        surface = Surface,
        onSurface = Ink,
        surfaceVariant = Sunken,
        onSurfaceVariant = Ink2,
        outline = Line,
        outlineVariant = Line2,
    )

    CompositionLocalProvider(
        LocalFlavorPalette provides flavorPalette,
        LocalDimens provides Dimens(),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = CriticalTechNewsTypography,
            shapes = CriticalTechNewsShapes,
            content = content,
        )
    }
}

object DsTheme {
    val flavor: FlavorPalette
        @Composable get() = LocalFlavorPalette.current
    val dimens: Dimens
        @Composable get() = LocalDimens.current
}
