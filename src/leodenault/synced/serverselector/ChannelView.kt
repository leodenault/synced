package leodenault.synced.serverselector

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.kord.common.entity.Snowflake
import leodenault.synced.ui.SelectableItem
import leodenault.synced.ui.SelectableItemColors

@Composable
fun Channel(
  modifier: Modifier = Modifier,
  name: String,
  isConnected: Boolean = false,
  onConnect: () -> Unit = {},
  onDisconnect: () -> Unit = {}
) {
  SelectableItem(
    modifier = modifier.height(IntrinsicSize.Min)
      .fillMaxWidth()
      .padding(5.dp),
    isSelected = isConnected,
    horizontalArrangement = Arrangement.SpaceBetween,
    colors = SelectableItemColors(
      background = MaterialTheme.colors.primary,
      selected = MaterialTheme.colors.secondary
    )
  ) {
    Column(
      modifier = Modifier.fillMaxHeight(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.Start
    ) {
      Text(
        modifier = Modifier.padding(5.dp),
        text = name,
        color = if (isConnected) MaterialTheme.colors.onSecondary else MaterialTheme.colors.onPrimary
      )
    }
    Column(
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.End
    ) {
      if (isConnected) {
        Button(
          modifier = Modifier.padding(5.dp),
          colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
          ),
          onClick = onDisconnect
        ) {
          Text(text = "Disconnect", color = MaterialTheme.colors.onPrimary)
        }
      } else {
        Button(
          modifier = Modifier.padding(5.dp),
          colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary
          ),
          onClick = onConnect
        ) {
          Text(text = "Connect", color = MaterialTheme.colors.onSecondary)
        }
      }
    }
  }
}
