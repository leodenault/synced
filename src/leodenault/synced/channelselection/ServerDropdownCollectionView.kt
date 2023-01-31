package leodenault.synced.channelselection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import leodenault.synced.channelselection.DropdownViewState.ServerData

@Composable
fun ServerDropdownCollection(
  serverList: List<ServerData>,
  selectedServer: ServerData?,
  onServerSelected: (ServerData) -> Unit
) {
  if (serverList.isEmpty()) {
    Text(
      modifier = Modifier.padding(5.dp),
      text = "No servers available"
    )
  }

  serverList.forEachIndexed { index, currentServerData ->
    val isServerSelected = selectedServer?.name == currentServerData.name
    val backgroundColor = if (isServerSelected) {
      MaterialTheme.colors.primary
    } else MaterialTheme.colors.background

    DropdownMenuItem(
      modifier = Modifier.background(backgroundColor),
      onClick = { onServerSelected(currentServerData) }
    ) {
      when {
        currentServerData is ServerData.WithIcon && isServerSelected -> ServerSelectable(
          text = currentServerData.name,
          iconData = currentServerData.iconData,
          textColor = MaterialTheme.colors.onPrimary,
          avatarSize = 36.dp,
        )

        currentServerData is ServerData.WithIcon && !isServerSelected -> ServerSelectable(
          text = currentServerData.name,
          iconData = currentServerData.iconData,
          avatarSize = 36.dp,
        )

        isServerSelected -> ServerSelectable(
          text = currentServerData.name,
          textColor = MaterialTheme.colors.onPrimary,
          avatarSize = 36.dp,
          avatarBackgroundColor = MaterialTheme.colors.secondary,
          avatarTextColor = MaterialTheme.colors.onSecondary,
          avatarFontSize = 16.sp
        )

        else -> ServerSelectable(
          text = currentServerData.name,
          avatarSize = 36.dp,
          avatarBackgroundColor = MaterialTheme.colors.primary,
          avatarTextColor = MaterialTheme.colors.onPrimary,
          avatarFontSize = 16.sp
        )
      }
    }

    if (index != serverList.lastIndex) {
      DropdownMenuItemDivider()
    }
  }
}
