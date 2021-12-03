package leodenault.synced.app

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import leodenault.synced.ui.TabView

@Composable
fun ApplicationFrame(
  tabDetails: List<TabDetails>,
  viewContent: @Composable (Modifier) -> Unit
) {
  var selectedTabIndex by remember { mutableStateOf(0) }

  TabView(
    modifier = Modifier.fillMaxWidth(),
    selectedTabIndex = selectedTabIndex,
    viewContent = viewContent,
    tabs = {
      tabDetails.forEachIndexed { index, tabDetails ->
        Tab(
          selected = selectedTabIndex == index,
          onClick = {
            if (selectedTabIndex != index) {
              selectedTabIndex = index
              tabDetails.onClick()
            }
          },
          text = { Text(text = tabDetails.name) }
        )
      }
    }
  )
}

class TabDetails(val name: String, val onClick: () -> Unit)
