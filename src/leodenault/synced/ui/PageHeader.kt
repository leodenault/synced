package leodenault.synced.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PageHeader(modifier: Modifier = Modifier, content: @Composable () -> @Composable Unit) {
  Row(
    modifier = modifier.background(color = MaterialTheme.colors.primary)
      .fillMaxWidth()
      .padding(4.dp)
  ) { content() }
}
