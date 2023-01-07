package leodenault.synced.serverselector

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import leodenault.synced.app.PageView
import leodenault.synced.ui.LoadingColumn

class ServerSelectorView(
  private val viewModel: ServerSelectorViewModel,
) : PageView {
  @Composable
  override fun render(modifier: Modifier) {
    Column(modifier = modifier) {
      ServerSelectorHeader(
        isLoggingOut = viewModel.isLoggingOut,
        onLogoutClick = viewModel::onLogoutClick
      )
      Row(
        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
      )
      {
        var selectedServerIndex by remember { mutableStateOf(-1) }

        val border = Modifier.border(
          1.dp,
          MaterialTheme.colors.onBackground,
          MaterialTheme.shapes.medium
        )
        val column = border.width(400.dp).heightIn(min = 50.dp)
        val title = border.padding(5.dp)

        Column {
          Text(modifier = title, text = "Available Servers")
          LoadingColumn(
            modifier = column,
            isLoading = viewModel.servers.isLoading
          ) {
            itemsIndexed(viewModel.servers.contents) { index, server ->
              ServerSelectable(
                text = server.name,
                iconData = server.icon.value,
                isSelected = selectedServerIndex == index,
                onSelect = {
                  if (selectedServerIndex != index) {
                    selectedServerIndex = index
                    server.onServerSelected()
                  }
                }
              )
            }
          }
        }

        Column {
          Text(modifier = title, text = "Available Channels")
          ChannelSelector(
            modifier = column,
            channels = viewModel.channels.contents,
            isLoading = if (viewModel.channels.contents.isEmpty()) {
              false
            } else {
              viewModel.channels.isLoading
            }
          )
        }
      }
    }
  }
}
