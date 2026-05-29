package dev.makethiswork.criticaltechnews.core.presentation.designsystem.components

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.makethiswork.criticaltechnews.core.presentation.designsystem.theme.DsTheme

@Composable
fun AppHairline(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier,
        thickness = DsTheme.dimens.hairline,
        color = MaterialTheme.colorScheme.outline,
    )
}
