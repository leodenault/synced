package leodenault.synced.app

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface PageView {
  @Composable
  fun render(modifier: Modifier)

  companion object {
    val EMPTY = object : PageView {
      @Composable
      override fun render(modifier: Modifier) {
        Text("This page is empty... :/")
      }
    }
  }
}
