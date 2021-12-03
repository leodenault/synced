package leodenault.synced.preview

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.runBlocking
import leodenault.synced.coroutines.CoroutineScopes
import leodenault.synced.player.PlayerFooter
import leodenault.synced.player.PlayerHeader

@Composable
fun Preview() {
  var isAudioPlaying by remember { mutableStateOf(false) }

  PlayerFooter(
    modifier = Modifier.fillMaxWidth(),
    isAudioPlaying = isAudioPlaying,
    onPlayButtonClick = {isAudioPlaying = !isAudioPlaying},
    activeTitle = "sum title"
  )
}

fun main() {
  runBlocking(CoroutineScopes.mainScope.coroutineContext) {
    application {
      Window(
        onCloseRequest = ::exitApplication,
        title = "Preview",
        state = rememberWindowState(width = 600.dp, height = 500.dp)
      ) {
        MaterialTheme {
          Preview()
        }
      }
    }
  }
}
