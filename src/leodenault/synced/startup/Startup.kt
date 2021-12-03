package leodenault.synced.startup

import androidx.compose.runtime.MutableState
import dagger.Reusable
import leodenault.synced.desktopaudio.AudioDirectoryFile
import leodenault.synced.desktopaudio.DesktopAudioListLoader
import leodenault.synced.discord.DisconnectedClient
import leodenault.synced.discordnavigation.DiscordNavigator
import leodenault.synced.playernavigation.PlayerNavigator
import leodenault.synced.settings.SettingsManager
import leodenault.synced.util.MutableData
import java.io.File
import javax.inject.Inject

@Reusable
class Startup @Inject constructor(
  private val discordNavigator: DiscordNavigator,
  private val playerNavigator: PlayerNavigator,
  private val mutableAudioFiles: MutableState<List<File>>,
  private val desktopAudioListLoader: DesktopAudioListLoader,
  private val mutableAudioDirectory: MutableData<File>,
  private val audioDirectoryFile: AudioDirectoryFile,
  private val settingsManager: SettingsManager,
  private val disconnectedClientFactory: DisconnectedClient.Factory
) {
  fun startup() {
    playerNavigator.navigateToPlayer()

    val settings = settingsManager.fetch()
    val disconnectedClient = disconnectedClientFactory.create()
    discordNavigator.navigateToLoadingPage {
      mutableAudioDirectory.value = audioDirectoryFile.load()
      mutableAudioFiles.value = desktopAudioListLoader.loadAudio()
      if (settings.hasBotToken()) {
        disconnectedClient.connect(settings.botToken).fold(
          onSuccess = { navigateToServerSelector(it) },
          onFailure = {
            navigateToBotAccess(
              disconnectedClient,
              "Could not automatically connect to Discord. Please re-enter bot token."
            )
          }
        )
      } else {
        navigateToBotAccess(disconnectedClient)
      }
    }
  }
}
