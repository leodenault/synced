package leodenault.synced.channelselection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun ServerAvatar(
  modifier: Modifier = Modifier,
  iconData: ImageBitmap
) = ServerAvatar(modifier, IconOrPlaceholder.Icon(iconData))

@Composable
fun ServerAvatar(
  modifier: Modifier = Modifier,
  placeholderChar: Char,
  fontSize: TextUnit = 36.sp,
  fontColor: Color = MaterialTheme.colors.onPrimary,
  backgroundColor: Color = MaterialTheme.colors.primary
) = ServerAvatar(
  modifier,
  IconOrPlaceholder.Placeholder(placeholderChar, fontSize, fontColor, backgroundColor)
)

@Composable
private fun ServerAvatar(
  modifier: Modifier = Modifier,
  iconOrPlaceholder: IconOrPlaceholder
) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    when (iconOrPlaceholder) {
      is IconOrPlaceholder.Placeholder -> {
        Column(
          modifier = Modifier.fillMaxSize().background(color = iconOrPlaceholder.backgroundColor),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            modifier = Modifier.width(IntrinsicSize.Min),
            text = iconOrPlaceholder.placeholderChar.toString(),
            color = iconOrPlaceholder.fontColor,
            fontSize = iconOrPlaceholder.fontSize,
            fontWeight = FontWeight.Bold
          )
        }
      }

      is IconOrPlaceholder.Icon -> {
        Image(
          bitmap = iconOrPlaceholder.iconData,
          contentDescription = "Discord server icon."
        )
      }
    }
  }
}

private sealed class IconOrPlaceholder private constructor() {
  class Icon(val iconData: ImageBitmap) : IconOrPlaceholder()
  class Placeholder(
    val placeholderChar: Char,
    val fontSize: TextUnit,
    val fontColor: Color,
    val backgroundColor: Color
  ) : IconOrPlaceholder()
}
