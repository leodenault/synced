package leodenault.synced.player

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.Reusable
import kotlinx.coroutines.launch
import leodenault.synced.coroutines.CoroutineScopes
import leodenault.synced.desktopaudio.AudioDirectoryFile
import leodenault.synced.desktopaudio.AudioFile
import leodenault.synced.desktopaudio.DesktopAudioListLoader
import leodenault.synced.discord.ConnectedClient
import leodenault.synced.navigation.Navigator
import leodenault.synced.channelselection.ChannelDropdownViewModel
import leodenault.synced.util.MutableData
import java.io.File
import javax.inject.Inject
import javax.swing.JFileChooser

class PlayerHeaderViewModel private constructor(
  private val navigator: Navigator,
  private val mutableAudioDirectory: MutableData<File>,
  private val audioDirectoryFile: AudioDirectoryFile,
  private val mutableAudioFiles: MutableState<List<AudioFile>>,
  private val desktopAudioListLoader: DesktopAudioListLoader,
  private val connectedClient: ConnectedClient,
  private val mutableConnectedClient: MutableData<ConnectedClient?>,
  searchBoxValue: MutableState<String>,
  channelDropdownViewModelFactory: ChannelDropdownViewModel.Factory,
  onChannelSelectedListeners: List<() -> Unit>,
  onChannelDeselectedListeners: List<() -> Unit>
) {
  val channelDropdownViewModel = channelDropdownViewModelFactory.create(
    connectedClient,
    onChannelSelectedListeners,
    onChannelDeselectedListeners
  )

  var searchBoxValue by searchBoxValue
    private set

  fun onSelectDirectory() {
    val directoryChooser = JFileChooser().apply {
      fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
      currentDirectory = mutableAudioDirectory.value
    }
    val chooseResult = directoryChooser.showDialog(null, "Select audio folder")
    if (chooseResult == JFileChooser.APPROVE_OPTION) {
      CoroutineScopes.viewModelScope.launch {
        mutableAudioDirectory.value = directoryChooser.selectedFile
        audioDirectoryFile.setLocation(directoryChooser.selectedFile)
        mutableAudioFiles.value = desktopAudioListLoader.loadAudio()
      }
    }
  }

  fun onSearchBoxValueChange(newValue: String) {
    searchBoxValue = newValue
  }

  fun onLogout() {
    navigator.navigateToLoadingPage {
      val disconnectedClient = connectedClient.disconnect()
      mutableConnectedClient.value = null
      navigator.navigateToBotAccess(disconnectedClient)
    }
  }

  @Reusable
  class Factory @Inject constructor(
    private val mutableAudioDirectory: MutableData<File>,
    private val audioDirectoryFile: AudioDirectoryFile,
    private val mutableAudioFiles: MutableState<List<AudioFile>>,
    private val desktopAudioListLoader: DesktopAudioListLoader,
    private val mutableConnectedClient: MutableData<ConnectedClient?>,
    private val channelDropdownViewModelFactory: ChannelDropdownViewModel.Factory
  ) {
    fun create(
      connectedClient: ConnectedClient,
      navigator: Navigator,
      searchBoxValue: MutableState<String>,
      onChannelSelectedListeners: List<() -> Unit>,
      onChannelDeselectedListeners: List<() -> Unit>
    ) =
      PlayerHeaderViewModel(
        navigator,
        mutableAudioDirectory,
        audioDirectoryFile,
        mutableAudioFiles,
        desktopAudioListLoader,
        connectedClient,
        mutableConnectedClient,
        searchBoxValue,
        channelDropdownViewModelFactory,
        onChannelSelectedListeners,
        onChannelDeselectedListeners
      )
  }
}