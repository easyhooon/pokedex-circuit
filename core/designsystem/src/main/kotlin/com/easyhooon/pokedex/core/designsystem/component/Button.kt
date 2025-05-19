package com.easyhooon.pokedex.core.designsystem.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.easyhooon.pokedex.core.common.MultipleEventsCutter
import com.easyhooon.pokedex.core.common.get
import com.easyhooon.pokedex.core.designsystem.ComponentPreview
import com.easyhooon.pokedex.core.designsystem.theme.PokedexTheme

@Composable
fun PokedexButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = Color.White,
    disabledContainerColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    disabledContentColor: Color = Color.White,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    val multipleEventsCutter = remember { MultipleEventsCutter.get() }
    Button(
        onClick = { multipleEventsCutter.processEvent { onClick() } },
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor,
        ),
        contentPadding = contentPadding,
        content = content,
    )
}

@ComponentPreview
@Composable
private fun PokedexButtonPreview() {
    PokedexTheme {
        PokedexButton(
            onClick = {},
        ) {
            Text("Button")
        }
    }
}

@ComponentPreview
@Composable
private fun PokedexDisabledButtonPreview() {
    PokedexTheme {
        PokedexButton(
            onClick = {},
            enabled = false,
        ) {
            Text("Button")
        }
    }
}
