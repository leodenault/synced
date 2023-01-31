package leodenault.synced.app

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface PageView {
  @Composable
  fun render()

  companion object {
    val EMPTY = object : PageView {
      @Composable
      override fun render() {
        Text("This page is empty... :/")
      }
    }
  }
}
