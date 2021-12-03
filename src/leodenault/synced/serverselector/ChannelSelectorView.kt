package leodenault.synced.serverselector

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import leodenault.synced.ui.LoadingColumn

@Composable
fun ChannelSelector(
  modifier: Modifier = Modifier,
  channels: List<ChannelViewModel>,
  isLoading: Boolean = true
) {
  var selectedChannelIndex by remember { mutableStateOf<Int?>(null) }

  LoadingColumn(
    modifier = modifier,
    isLoading = isLoading,
    verticalArrangement = Arrangement.SpaceBetween
  ) {
    itemsIndexed(channels) { index, channel ->
      Channel(
        name = channel.name,
        isConnected = selectedChannelIndex == index,
        onConnect = {
          if (selectedChannelIndex != index) {
            channel.onChannelSelected()
            selectedChannelIndex = index
          }
        },
        onDisconnect = {
          channel.onChannelLeave()
          selectedChannelIndex = null
        }
      )
    }
  }
}
