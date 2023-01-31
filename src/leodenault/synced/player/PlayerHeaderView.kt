package leodenault.synced.player

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import leodenault.synced.ui.PageHeader
import leodenault.synced.ui.SearchBox

@Composable
fun PlayerHeader(viewModel: PlayerHeaderViewModel) {
  PageHeader {
    Box(
      modifier = Modifier.fillMaxWidth()
    ) {
      Row (
        modifier = Modifier.align(Alignment.CenterStart)
      ) {
        Button(
          modifier = Modifier.padding(2.dp),
          colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = MaterialTheme.colors.onSecondary
          ),
          onClick = viewModel::onLogout
        ) {
          Text(text = "Logout")
        }
        Spacer(modifier = Modifier.width(5.dp))
        Button(
          modifier = Modifier.padding(2.dp),
          colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = MaterialTheme.colors.onSecondary
          ),
          onClick = viewModel::onSelectDirectory
        ) {
          Text(text = "Select audio folder")
        }
      }

      SearchBox(
        modifier = Modifier.padding(5.dp).align(Alignment.Center).fillMaxWidth(0.33f),
        value = viewModel.searchBoxValue,
        placeholder = "Search for audio...",
        onValueChange = viewModel::onSearchBoxValueChange
      )

      leodenault.synced.channelselection.ChannelSelectorDropdown(
        modifier = Modifier.align(Alignment.CenterEnd),
        viewModel = viewModel.channelDropdownViewModel
      )
    }
  }
}
