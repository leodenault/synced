package leodenault.synced.audioselector

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import leodenault.synced.ui.SelectableItem

@Composable
fun AudioTrack(
    modifier: Modifier = Modifier,
    name: String,
    isSelected: Boolean = false,
    isEnabled: Boolean = true,
    onSelect: () -> Unit = {},
    onDoubleTap: () -> Unit = {},
) {
    SelectableItem(
        modifier = modifier.fillMaxWidth().padding(start = 5.dp),
        isSelected = isSelected,
        onSelect = onSelect,
        onDoubleTap = onDoubleTap,
        isEnabled = isEnabled,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val style = if (isEnabled) TrackStyle.Enabled(
            name,
            MaterialTheme.colors.onBackground
        ) else TrackStyle.Disabled(name, Color(0xFF4A4A4A))

        Text(
            text = style.text,
            fontStyle = style.fontStyle,
            color = style.textColor
        )
    }
}

private sealed class TrackStyle(val text: String, val fontStyle: FontStyle, val textColor: Color) {
    class Enabled(text: String, textColor: Color) :
        TrackStyle(text, FontStyle.Normal, textColor)

    class Disabled(text: String, textColor: Color) :
        TrackStyle(
            "$text (Unsupported file format)",
            FontStyle.Italic,
            textColor
        )
}
