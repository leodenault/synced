package leodenault.synced.channelselection

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import leodenault.synced.channelselection.DropdownViewState.ChannelData
import leodenault.synced.channelselection.DropdownViewState.ServerData

private val disabledColor = Color(0xffdbdbdb)

@Composable
fun ChannelSelectorButton(
  modifier: Modifier = Modifier,
  isLoading: Boolean,
  selectedChannel: ChannelData?,
  onClick: () -> Unit = {}
) {
  val backgroundColor =
    if (isLoading) disabledColor else MaterialTheme.colors.primary
  var rowModifier = modifier.clip(RoundedCornerShape(4.dp))
  if (!isLoading) {
    rowModifier = rowModifier.clickable { onClick() }
  }
  rowModifier = rowModifier.border(ButtonDefaults.outlinedBorder)
    .background(color = backgroundColor)
    .padding(10.dp)

  Row(
    modifier = rowModifier,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    if (isLoading) {
      Column(
        modifier = Modifier.width(IntrinsicSize.Min),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          modifier = Modifier.padding(bottom = 2.dp),
          text = "Loading available channels",
          color = MaterialTheme.colors.onBackground
        )
        LinearProgressIndicator(
          modifier = Modifier.fillMaxWidth(),
          color = MaterialTheme.colors.onBackground,
          backgroundColor = disabledColor
        )
      }
    } else {
      if (selectedChannel == null) {
        Text("Connect to a channel", color = MaterialTheme.colors.onPrimary)
      } else {
        val server = selectedChannel.server
        if (server is ServerData.WithIcon) {
          ServerAvatar(
            modifier = Modifier.size(30.dp).padding(5.dp),
            iconData = server.iconData
          )
        } else {
          ServerAvatar(
            modifier = Modifier.size(30.dp).padding(5.dp),
            placeholderChar = server.name.first(),
            fontSize = 15.sp,
            backgroundColor = MaterialTheme.colors.secondary,
            fontColor = MaterialTheme.colors.onSecondary
          )
        }

        Text(
          modifier = Modifier.padding(5.dp),
          text = selectedChannel.channel.name,
          color = MaterialTheme.colors.onPrimary
        )
      }
    }
  }
}
