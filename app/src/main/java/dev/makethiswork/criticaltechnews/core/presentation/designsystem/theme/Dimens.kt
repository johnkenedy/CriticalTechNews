package dev.makethiswork.criticaltechnews.core.presentation.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    val space4: Dp = 4.dp,
    val space7: Dp = 7.dp,
    val space8: Dp = 8.dp,
    val space12: Dp = 12.dp,
    val space14: Dp = 14.dp,
    val space16: Dp = 16.dp,
    val space20: Dp = 20.dp,
    val space22: Dp = 22.dp,

    val radiusThumb: Dp = 7.dp,
    val radiusImage: Dp = 8.dp,
    val radiusCard: Dp = 10.dp,
    val radiusChip: Dp = 16.dp,

    val hairline: Dp = 1.dp,
    val touchTarget: Dp = 48.dp,
    val iconButtonVisual: Dp = 36.dp,
    val pillHeight: Dp = 44.dp,
    val rowImage: Dp = 92.dp,
    val avatar: Dp = 30.dp,
    val lockRing: Dp = 108.dp,
    val stateGlyph: Dp = 72.dp,

    val screenHorizontal: Dp = 20.dp,
)

val LocalDimens = staticCompositionLocalOf { Dimens() }
