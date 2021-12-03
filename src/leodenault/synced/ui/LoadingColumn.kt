package leodenault.synced.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoadingColumn(
  modifier: Modifier = Modifier,
  isLoading: Boolean = true,
  verticalArrangement: Arrangement.Vertical = Arrangement.Top,
  contents: LazyListScope.() -> Unit
) {
  LazyColumn(
    modifier = modifier,
    verticalArrangement = verticalArrangement
  ) {
    apply(contents)

    if (isLoading) {
      item {
        Row(Modifier.fillParentMaxWidth(), horizontalArrangement = Arrangement.Center) {
          CircularProgressIndicator()
        }
      }
    }
  }
}
