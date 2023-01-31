package leodenault.synced.channelselection

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ServerSelectable(
  modifier: Modifier = Modifier,
  text: String,
  textColor: Color = MaterialTheme.colors.onBackground,
  avatarSize: Dp = 60.dp,
  avatarBackgroundColor: Color = MaterialTheme.colors.primary,
  avatarTextColor: Color = MaterialTheme.colors.onPrimary,
  avatarFontSize: TextUnit = 36.sp
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .height(IntrinsicSize.Min)
      .padding(5.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    ServerAvatar(
      modifier = Modifier.size(avatarSize),
      placeholderChar = text.first(),
      backgroundColor = avatarBackgroundColor,
      fontColor = avatarTextColor,
      fontSize = avatarFontSize
    )

    Text(text, modifier = Modifier.padding(start = 10.dp), color = textColor)
  }
}

@Composable
fun ServerSelectable(
  modifier: Modifier = Modifier,
  text: String,
  iconData: ImageBitmap,
  textColor: Color = MaterialTheme.colors.onBackground,
  avatarSize: Dp = 60.dp
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .height(IntrinsicSize.Min)
      .padding(5.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    ServerAvatar(modifier = Modifier.size(avatarSize), iconData = iconData)

    Text(text, modifier = Modifier.padding(start = 10.dp), color = textColor)
  }
}
