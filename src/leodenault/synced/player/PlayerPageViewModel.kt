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
import leodenault.synced.desktopaudio.AudioDirectoryFile
import leodenault.synced.desktopaudio.AudioFile
import leodenault.synced.desktopaudio.DesktopAudioListLoader
import leodenault.synced.discord.ConnectedClient
import leodenault.synced.navigation.Navigator
import leodenault.synced.util.MutableData
import java.io.File
import javax.inject.Inject
import javax.swing.JFileChooser

class PlayerPageViewModel private constructor(
  private val audioTrackViewModelFactory: AudioTrackViewModel.Factory,
  private val mutableAudioFiles: MutableState<List<AudioFile>>,
  private val activeAudioStream: MutableState<AudioStream?>,
  private val audioItemLoader: AudioItemLoader,
  playerHeaderViewModelFactory: PlayerHeaderViewModel.Factory,
  connectedClient: ConnectedClient,
  navigator: Navigator
) {
  val isAudioPlaying by derivedStateOf {
    activeAudioStream.value.let { if (it == null) false else !it.isPaused.value }
  }
  val audioSelectorViewModel = AudioSelectorViewModel(derivedStateOf {
    mutableAudioFiles.value.filter {
      it.javaFile.name.contains(other = searchBoxValue, ignoreCase = true)
    }.sortedBy { it.javaFile.name }.toViewModels()
  })
  val activeTitle by derivedStateOf { activeAudioStream.value?.title }
  val playerHeaderViewModel = playerHeaderViewModelFactory.create(
    connectedClient,
    navigator,
    onChannelSelectedListeners = listOf { arePlayerControlsEnabled = true },
    onChannelDeselectedListeners = listOf {
      stopCurrentTrack()
      arePlayerControlsEnabled = false
    }
  )

  var arePlayerControlsEnabled by mutableStateOf(false)

  var searchBoxValue by mutableStateOf("")
    private set

  fun playCurrentTrack() {
    val stream = activeAudioStream.value
    val selectedTrack = audioSelectorViewModel.selectedAudioTrack.value

    when {
      stream == null -> if (selectedTrack == null) return else {
        loadAndPlayTrack(selectedTrack.track)
      }

      isAudioPlaying -> stream.pause()
      else -> stream.resume()
    }
  }

  fun onTrackDoubleTapped(audioTrack: AudioTrackViewModel) {
    activeAudioStream.value?.close()
    loadAndPlayTrack(audioTrack)
  }

  fun onPlayNextTrack() = playTrackOffsetBy(1)

  fun onPlayPreviousTrack() = playTrackOffsetBy(-1)

  fun stopCurrentTrack() {
    activeAudioStream.value?.close()
    activeAudioStream.value = null
  }

  private fun playTrackOffsetBy(offsetIndex: Int) {
    val selectedTrack = audioSelectorViewModel.selectedAudioTrack.value ?: return
    val allAudioTracks = audioSelectorViewModel.audioTracks
    var nextTrackIndex = selectedTrack.index
    do {
      nextTrackIndex =
        Math.floorMod(nextTrackIndex + offsetIndex, allAudioTracks.size)
    } while (
      nextTrackIndex != selectedTrack.index && !allAudioTracks[nextTrackIndex].isSupported)

    val newlySelectedTrack = allAudioTracks[nextTrackIndex]
    audioSelectorViewModel.onSelect(newlySelectedTrack)
    loadAndPlayTrack(newlySelectedTrack)
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