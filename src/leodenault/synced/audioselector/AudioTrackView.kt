package leodenault.synced.audioselector

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import leodenault.synced.ui.SelectableItem

@Composable
fun AudioTrack(
  modifier: Modifier = Modifier,
  name: String,
  isSelected: Boolean = false,
  isEnabled: Boolean = true,
  onSelect: () -> Unit = {},
  onDoubleTap: () -> Unit = {},
) {
  SelectableItem(
    modifier = modifier.fillMaxWidth(),
    isSelected = isSelected,
    onSelect = onSelect,
    onDoubleTap = onDoubleTap,
    isEnabled = isEnabled,
  ) {
    Text(text = name)
  }
}
