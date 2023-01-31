package leodenault.synced.channelselection

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DropdownMenuItemDivider() {
  Divider(
    modifier = Modifier.fillMaxWidth(0.95f).padding(vertical = 2.dp).height(1.dp),
    color = MaterialTheme.colors.onBackground
  )
}
