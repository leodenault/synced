package leodenault.synced.app

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.github.kwhat.jnativehook.GlobalScreen
import kotlinx.coroutines.runBlocking
import leodenault.synced.discord.ConnectedClient
import leodenault.synced.navigation.Navigator
import leodenault.synced.startup.Startup
import leodenault.synced.util.Data
import javax.inject.Inject

class Application @Inject constructor(
  private val startup: Startup,
  private val navigator: Navigator,
  private val connectedClient: Data<ConnectedClient?>,
) {

  fun run() {
    GlobalScreen.registerNativeHook()
    startup.startup()

    application {
      Window(
        onCloseRequest = {
          runBlocking { connectedClient.value?.disconnect() }
          GlobalScreen.unregisterNativeHook()
          exitApplication()
        },
        title = "Synced",
        state = rememberWindowState(width = 1280.dp, height = 720.dp)
      ) {
        MaterialTheme {
          navigator.currentPageView.render()
        }
      }
    }
  }
}