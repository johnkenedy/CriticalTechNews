package dev.makethiswork.criticaltechnews.core.presentation.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import dev.makethiswork.criticaltechnews.R

val Paper = Color(0xFFFAF8F4)
val Surface = Color(0xFFFFFFFF)
val Sunken = Color(0xFFF1EEE7)
val Line = Color(0xFFE8E3D8)
val Line2 = Color(0xFFD9D3C6)

val Ink = Color(0xFF16140F)
val Ink2 = Color(0xFF585249)
val Ink3 = Color(0xFF8E867A)

val Skel = Color(0xFFECE8E0)
val Skel2 = Color(0xFFE1DCD2)
val SuccessGreen = Color(0xFF1E7F4E)

val BbcAccent = Color(0xFFC8102E)
val BbcAccentDim = Color(0xFF9E0C24)
val CnnAccent = Color(0xFFCC0000)
val CnnMastheadBg = Color(0xFF1A1714)

data class FlavorPalette(
    val accent: Color,
    val accentDim: Color,
    val mastheadBg: Color,
    val mastheadOn: Color,
)

val LocalFlavorPalette = compositionLocalOf<FlavorPalette> {
    error("FlavorPalette not provided — wrap content in CriticalTechNewsTheme.")
}

@Composable
@ReadOnlyComposable
internal fun rememberFlavorPalette(): FlavorPalette = FlavorPalette(
    accent = colorResource(R.color.ds_accent),
    accentDim = colorResource(R.color.ds_accent_dim),
    mastheadBg = colorResource(R.color.ds_masthead_bg),
    mastheadOn = colorResource(R.color.ds_masthead_on),
)
