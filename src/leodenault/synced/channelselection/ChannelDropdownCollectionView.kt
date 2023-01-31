package leodenault.synced.channelselection

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.kord.core.entity.channel.VoiceChannel
import leodenault.synced.channelselection.DropdownViewState.ChannelData
import leodenault.synced.channelselection.DropdownViewState.ServerData

@Composable
fun ChannelDropdownCollection(
  selectedServer: ServerData,
  selectedChannel: VoiceChannel?,
  onNavigateBack: () -> Unit,
  onChannelSelected: (selectedChannel: ChannelData) -> Unit,
  onChannelDeselected: (selectedChannel: ChannelData) -> Unit,
) {
  Row(
    modifier = Modifier.fillMaxWidth()
      .clickable { onNavigateBack() }
      .padding(5.dp),
    horizontalArrangement = Arrangement.Start,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(
      imageVector = Icons.Filled.ArrowBack,
      contentDescription = "Navigate back to server selection."
    )
  }

  if (selectedServer.channels.isEmpty()) {
    Text(
      modifier = Modifier.padding(5.dp),
      text = "No channels available"
    )
  }

  selectedServer.channels.forEach { channel ->
    val isChannelConnected = selectedChannel == channel

    DropdownMenuItemDivider()

    val ctaText: String
    val channelColors: @Composable MutableChannelColors.() -> Unit
    val onCtaClick: () -> Unit
    if (isChannelConnected) {
      ctaText = "Disconnect"
      channelColors = {
        background = MaterialTheme.colors.primary
        text = MaterialTheme.colors.onPrimary
        buttonColors = {
          background = MaterialTheme.colors.secondary
          text = MaterialTheme.colors.onSecondary
        }
      }
      onCtaClick = { onChannelDeselected(ChannelData(selectedServer, channel)) }
    } else {
      ctaText = "Connect"
      channelColors = {}
      onCtaClick = { onChannelSelected(ChannelData(selectedServer, channel)) }
    }

    Channel(
      name = channel.name,
      ctaText = ctaText,
      onCtaClick = onCtaClick,
      channelColors = channelColors
    )
  }
}
