package leodenault.synced.player

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import leodenault.synced.audio.AudioItemLoader
import leodenault.synced.audio.AudioStream
import leodenault.synced.audioselector.AudioTrackViewModel
import leodenault.synced.coroutines.CoroutineScopes
import leodenault.synced.desktopaudio.AudioFile
import leodenault.synced.discord.ConnectedClient
import leodenault.synced.navigation.Navigator
import javax.inject.Inject

class PlayerPageViewModel private constructor(
  private val audioTrackViewModelFactory: AudioTrackViewModel.Factory,
  private val mutableAudioFiles: MutableState<List<AudioFile>>,
  private val activeAudioStream: MutableState<AudioStream?>,
  private val audioItemLoader: AudioItemLoader,
  playerHeaderViewModelFactory: PlayerHeaderViewModel.Factory,
  connectedClient: ConnectedClient,
  navigator: Navigator
) {
  var isChannelSelected = mutableStateOf(false)
  val isAudioPlaying by derivedStateOf {
    activeAudioStream.value.let { if (it == null) false else !it.isPaused.value }
  }
  val audioSelectorViewModel = AudioSelectorViewModel(derivedStateOf {
    mutableAudioFiles.value.filter {
      it.javaFile.name.contains(other = searchBoxValue, ignoreCase = true)
    }.sortedBy { it.javaFile.name }.toViewModels()
  }, isChannelSelected, this::onTrackSelected)
  val activeTitle by derivedStateOf { activeAudioStream.value?.title }
  val playerHeaderViewModel = playerHeaderViewModelFactory.create(
    connectedClient,
    navigator,
    onChannelSelectedListeners = listOf { isChannelSelected.value = true },
    onChannelDeselectedListeners = listOf {
      stopCurrentTrack()
      isChannelSelected.value = false
    }
  )

  var searchBoxValue by mutableStateOf("")
    private set

  fun onPlayCurrentTrack() {
    val stream = activeAudioStream.value
    val selectedTrack = audioSelectorViewModel.selectedTrack

    when {
      stream == null -> {
        if (selectedTrack == null) return else loadAndPlayTrack(selectedTrack)
      }

      isAudioPlaying -> stream.pause()
      else -> stream.resume()
    }
  }

  private fun onTrackSelected(audioTrack: AudioTrackViewModel) {
    activeAudioStream.value?.close()
    loadAndPlayTrack(audioTrack)
  }

  fun onPlayNextTrack() = audioSelectorViewModel.onSelectNextTrack()

  fun onPlayPreviousTrack() = audioSelectorViewModel.onSelectPreviousTrack()

  fun stopCurrentTrack() {
    activeAudioStream.value?.close()
    activeAudioStream.value = null
  }

  private fun loadAndPlayTrack(audioTrack: AudioTrackViewModel) {
    CoroutineScopes.ioScope.launch {
      activeAudioStream.value = audioItemLoader.load(audioTrack.path)
      activeAudioStream.value?.resume()
    }
  }

  private fun List<AudioFile>.toViewModels() = this.map {
    audioTrackViewModelFactory.create(it)
  }

  class Factory @Inject constructor(
    private val audioTrackViewModelFactory: AudioTrackViewModel.Factory,
    private val mutableAudioFiles: MutableState<List<AudioFile>>,
    private val activeAudioStream: MutableState<AudioStream?>,
    private val audioItemLoader: AudioItemLoader,
    private val playerHeaderViewModelFactory: PlayerHeaderViewModel.Factory
  ) {
    fun create(connectedClient: ConnectedClient, navigator: Navigator) = PlayerPageViewModel(
      audioTrackViewModelFactory,
      mutableAudioFiles,
      activeAudioStream,
      audioItemLoader,
      playerHeaderViewModelFactory,
      connectedClient,
      navigator
    )
  }
}