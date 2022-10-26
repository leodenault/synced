package leodenault.synced.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun SelectableItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    isEnabled: Boolean = true,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    onSelect: () -> Unit = {},
    onDoubleTap: () -> Unit = {},
    colors: SelectableItemColors = SelectableItemColors(
        background = MaterialTheme.colors.background,
        selected = MaterialTheme.colors.secondary
    ),
    contents: @Composable () -> Unit
) {
    var isUnderPointer by remember { mutableStateOf(false) }

    val backgroundColor = when {
        !isEnabled -> SelectableItemColors.HOVER
        isSelected && isUnderPointer -> colors.selected * 0.8f
        isSelected -> colors.selected
        isUnderPointer -> SelectableItemColors.HOVER
        else -> colors.background
    }
    var selectableModifier =
        Modifier.background(color = backgroundColor).then(modifier).heightIn(min = 30.dp)
    if (isEnabled) {
        selectableModifier = selectableModifier.selectable(
            selected = isSelected,
            enabled = isEnabled,
            role = Role.RadioButton,
            onClick = onSelect
        ).pointerInput(Unit) {
            detectTapGestures(
                onDoubleTap = { onDoubleTap() },
                onPress = { onSelect() }
            )
        }.pointerMoveFilter(
            onEnter = {
                isUnderPointer = true
                false
            },
            onExit = {
                isUnderPointer = false
                false
            }
        )
    }

    Row(
        modifier = selectableModifier,
        verticalAlignment = verticalAlignment,
        horizontalArrangement = horizontalArrangement
    ) {
        contents()
    }
}

class SelectableItemColors(
    val background: Color,
    val selected: Color
) {
    companion object {
        val HOVER = Color(0xFFE8E8E8)
    }
}

private operator fun Color.times(multiplier: Float) = Color(
  red = red * multiplier,
  green = green * multiplier,
  blue = blue * multiplier,
  alpha = 1f
)
