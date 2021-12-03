package leodenault.synced.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import leodenault.synced.app.PageView

class ErrorPageView(private val message: String) : PageView {
  @Composable
  override fun render(modifier: Modifier) {
    Column(
      modifier = modifier,
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(text = "Error!")
      Text(text = message)
    }
  }
}
