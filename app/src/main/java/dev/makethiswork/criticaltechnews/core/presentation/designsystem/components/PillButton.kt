package dev.makethiswork.criticaltechnews.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.makethiswork.criticaltechnews.core.presentation.designsystem.theme.DsTheme
import dev.makethiswork.criticaltechnews.core.presentation.designsystem.theme.SansFamily
import dev.makethiswork.criticaltechnews.core.presentation.designsystem.theme.Surface

@Composable
fun PillButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
) {
    val flavor = DsTheme.flavor
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = DsTheme.dimens.pillHeight),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = flavor.accent,
            contentColor = Surface,
        ),
        contentPadding = PaddingValues(horizontal = 22.dp, vertical = 0.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                )
            }
            Text(
                text = label,
                fontFamily = SansFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
            )
        }
    }
}
