package com.easyhooon.pokedex.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.easyhooon.pokedex.core.designsystem.ComponentPreview
import com.easyhooon.pokedex.core.designsystem.theme.PokedexTheme

@Composable
fun PokedexOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = MaterialTheme.colorScheme.primary,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    enabled: Boolean = true,
    cornerRadius: Dp = 10.dp,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(cornerRadius),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        border = BorderStroke(1.dp, borderColor),
        contentPadding = contentPadding,
        content = content,
    )
}

@ComponentPreview
@Composable
private fun PokedexMarketOutlinedButtonPreview() {
    PokedexTheme {
        PokedexOutlinedButton(
            onClick = {},
        ) {
            Text("Outlined Button")
        }
    }
}
