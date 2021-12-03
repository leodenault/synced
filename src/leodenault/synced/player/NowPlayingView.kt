package leodenault.synced.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NowPlaying(
  modifier: Modifier = Modifier,
  title: String? = null
) {
  if (!title.isNullOrEmpty()) {
    Row(
      modifier = modifier.padding(5.dp)
        .background(color = MaterialTheme.colors.secondary, shape = MaterialTheme.shapes.small)
        .padding(5.dp)
    ) {
      Text(text = "Now playing: $title", color = MaterialTheme.colors.onSecondary)
    }
  }
}
