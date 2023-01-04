package leodenault.synced.player

import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import leodenault.synced.audio.AudioItemLoader
import leodenault.synced.audio.AudioStream
import leodenault.synced.audioselector.AudioTrackViewModel
import leodenault.synced.coroutines.CoroutineScopes
import leodenault.synced.desktopaudio.AudioDirectoryFile
import leodenault.synced.desktopaudio.AudioFile
import leodenault.synced.desktopaudio.DesktopAudioListLoader
import leodenault.synced.util.MutableData
import java.io.File
import javax.inject.Inject
import javax.swing.JFileChooser

class PlayerPageViewModel private constructor(
    private val audioTrackViewModelFactory: AudioTrackViewModel.Factory,
    private val mutableAudioFiles: MutableState<List<AudioFile>>,
    private val desktopAudioListLoader: DesktopAudioListLoader,
    private val mutableAudioDirectory: MutableData<File>,
    private val audioDirectoryFile: AudioDirectoryFile,
    private val activeAudioStream: MutableState<AudioStream?>,
    private val audioItemLoader: AudioItemLoader,
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

    var searchBoxValue by mutableStateOf("")
        private set
    var isSelectingDirectory by mutableStateOf(false)
        private set

    fun onSelectDirectory() {
        isSelectingDirectory = true
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
                isSelectingDirectory = false
            }
        } else {
            isSelectingDirectory = false
        }
    }

    fun onSearchBoxChange(newValue: String) {
        searchBoxValue = newValue
    }

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
        private val desktopAudioListLoader: DesktopAudioListLoader,
        private val mutableAudioDirectory: MutableData<File>,
        private val audioDirectoryFile: AudioDirectoryFile,
        private val activeAudioStream: MutableState<AudioStream?>,
        private val audioItemLoader: AudioItemLoader
    ) {
        fun create() = PlayerPageViewModel(
            audioTrackViewModelFactory,
            mutableAudioFiles,
            desktopAudioListLoader,
            mutableAudioDirectory,
            audioDirectoryFile,
            activeAudioStream,
            audioItemLoader
        )
    }
}