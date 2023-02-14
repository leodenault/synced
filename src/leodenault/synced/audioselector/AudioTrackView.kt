package leodenault.synced.audioselector

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp

private val disabledTextColor = Color(0xFF4A4A4A)
private val disabledIconColor = Color(0xFF828282)
private val hoverColor = Color(0xFFE8E8E8)
private val selectedColor
  @Composable
  get() = MaterialTheme.colors.secondary
private val unselectedColor
  @Composable
  get() = MaterialTheme.colors.background

@Composable
fun AudioTrack(
  modifier: Modifier = Modifier,
  name: String,
  isSelected: Boolean = false,
  isEnabled: Boolean = true,
  isPlayable: Boolean = true,
  onSelect: () -> Unit = {}
) {
  val interactionSource = remember { MutableInteractionSource() }
  val isHovered by interactionSource.collectIsHoveredAsState()
  val backgroundColor = when {
    !isEnabled -> hoverColor
    isSelected && isHovered -> selectedColor * 0.8f
    isSelected -> selectedColor
    isHovered -> hoverColor
    else -> unselectedColor
  }
  Row(
    modifier = Modifier.background(color = backgroundColor)
      .then(other = modifier.fillMaxWidth().padding(start = 5.dp)).hoverable(interactionSource),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Row(
      modifier = modifier.width(50.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      if (isEnabled && isHovered && !isSelected) {
        IconButton(
          modifier = Modifier.fillMaxHeight(),
          enabled = isPlayable,
          onClick = onSelect,
        ) {
          Icon(
            Icons.Filled.PlayArrow,
            contentDescription = "Play track",
            tint = if (isPlayable) MaterialTheme.colors.onBackground else disabledIconColor
          )
        }
      }
    }

    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Start
    ) {
      val style = if (isEnabled) TrackStyle.Enabled(
        name,
        MaterialTheme.colors.onBackground
      ) else {
        TrackStyle.Disabled(name, disabledTextColor)
      }
      Text(
        text = style.text,
        fontStyle = style.fontStyle,
        color = style.textColor
      )
    }
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

private operator fun Color.times(multiplier: Float) = Color(
  red = red * multiplier,
  green = green * multiplier,
  blue = blue * multiplier,
  alpha = 1f
)