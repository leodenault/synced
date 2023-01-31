package leodenault.synced.startup

import androidx.compose.runtime.MutableState
import dagger.Reusable
import leodenault.synced.desktopaudio.AudioDirectoryFile
import leodenault.synced.desktopaudio.AudioFile
import leodenault.synced.desktopaudio.DesktopAudioListLoader
import leodenault.synced.discord.DisconnectedClient
import leodenault.synced.navigation.Navigator
import leodenault.synced.settings.SettingsManager
import leodenault.synced.util.MutableData
import java.io.File
import javax.inject.Inject

@Reusable
class Startup @Inject constructor(
  private val navigator: Navigator,
  private val mutableAudioFiles: MutableState<List<AudioFile>>,
  private val desktopAudioListLoader: DesktopAudioListLoader,
  private val mutableAudioDirectory: MutableData<File>,
  private val audioDirectoryFile: AudioDirectoryFile,
  private val settingsManager: SettingsManager,
  private val disconnectedClientFactory: DisconnectedClient.Factory
) {
  fun startup() {
    val settings = settingsManager.fetch()
    val disconnectedClient = disconnectedClientFactory.create()
    navigator.navigateToLoadingPage {
      mutableAudioDirectory.value = audioDirectoryFile.load()
      mutableAudioFiles.value = desktopAudioListLoader.loadAudio()
      if (settings.hasBotToken()) {
        disconnectedClient.connect(settings.botToken).fold(
          onSuccess = { navigateToPlayer(it) },
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
