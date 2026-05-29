package dev.makethiswork.criticaltechnews.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.makethiswork.criticaltechnews.core.presentation.designsystem.theme.DsTheme

@Composable
fun DateDivider(
    label: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = DsTheme.dimens.screenHorizontal,
                end = DsTheme.dimens.screenHorizontal,
                top = 18.dp,
                bottom = 8.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        EyebrowText(text = label)
        AppHairline(modifier = Modifier.weight(1f))
    }
}
