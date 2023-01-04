package leodenault.synced.app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.github.kwhat.jnativehook.GlobalScreen
import kotlinx.coroutines.runBlocking
import leodenault.synced.discord.ConnectedClient
import leodenault.synced.discordnavigation.DiscordNavigator
import leodenault.synced.navigation.Navigator
import leodenault.synced.playernavigation.PlayerNavigator
import leodenault.synced.startup.Startup
import leodenault.synced.util.Data
import javax.inject.Inject

class Application @Inject constructor(
  private val startup: Startup,
  private val discordNavigator: DiscordNavigator,
  private val playerNavigator: PlayerNavigator,
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
          var activeNavigator by remember { mutableStateOf<Navigator>(discordNavigator) }

          ApplicationFrame(
            listOf(
              TabDetails("Discord Client") { activeNavigator = discordNavigator },
              TabDetails("Player") { activeNavigator = playerNavigator }
            )
          ) { modifier ->
            activeNavigator.currentPageView.render(modifier = modifier)
          }
        }
      }
    }
  }
}