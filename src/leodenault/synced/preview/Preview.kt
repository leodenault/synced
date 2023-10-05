package leodenault.synced.preview

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.runBlocking
import leodenault.synced.coroutines.CoroutineScopes
import leodenault.synced.player.PlayerControls

@Composable
fun Preview() {
  val isAudioPlaying = remember { mutableStateOf(false) }

  PlayerControls(isAudioPlaying = isAudioPlaying.value, onPlayButtonClick = {isAudioPlaying.value = !isAudioPlaying.value})
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
