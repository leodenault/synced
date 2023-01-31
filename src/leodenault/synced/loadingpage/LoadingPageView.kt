package leodenault.synced.loadingpage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.unit.dp
import leodenault.synced.app.PageView

class LoadingPageView(private val viewModel: LoadingPageViewModel) : PageView {
  @Composable
  override fun render() {
    LaunchedEffect("") { viewModel.load() }
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      CircularProgressIndicator(modifier = Modifier.fillMaxSize(0.33f), strokeWidth = 5.dp)
    }
  }
}
