package leodenault.synced.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TabView(
  modifier: Modifier = Modifier,
  selectedTabIndex: Int,
  viewContent: @Composable (Modifier) -> Unit,
  tabs: @Composable () -> Unit
) {
  Column(modifier = modifier) {
    viewContent(Modifier.fillMaxHeight(0.93f).fillMaxWidth())
    TabRow(
      modifier = Modifier.fillMaxHeight().fillMaxWidth(),
      selectedTabIndex = selectedTabIndex,
      tabs = tabs
    )
  }
}
