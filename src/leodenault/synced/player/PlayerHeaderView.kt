package leodenault.synced.player

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun PlayerHeader(
  onDirectorySelectClick: () -> Unit,
  searchBoxValue: String,
  onSearchBoxValueChange: (String) -> Unit
) {
  PageHeader {
    Box(
      modifier = Modifier.fillMaxWidth()
    ) {
      Button(
        modifier = Modifier.padding(2.dp).align(Alignment.CenterStart),
        colors = ButtonDefaults.buttonColors(
          backgroundColor = MaterialTheme.colors.secondary,
          contentColor = MaterialTheme.colors.onSecondary
        ),
        onClick = onDirectorySelectClick
      ) {
        Text(text = "Select audio folder")
      }

      SearchBox(
        modifier = Modifier.padding(5.dp).align(Alignment.Center).fillMaxWidth(0.33f),
        value = searchBoxValue,
        placeholder = "Search for audio...",
        onValueChange = onSearchBoxValueChange
      )
    }
  }
}
