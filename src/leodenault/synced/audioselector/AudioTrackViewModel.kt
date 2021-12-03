package leodenault.synced.audioselector

import androidx.compose.runtime.MutableState
import dagger.Reusable
import kotlinx.coroutines.launch
import leodenault.synced.audio.AudioItemLoader
import leodenault.synced.audio.AudioStream
import leodenault.synced.coroutines.CoroutineScopes
import leodenault.synced.util.MutableData
import java.io.File
import javax.inject.Inject

class AudioTrackViewModel private constructor(
  private val file: File,
//  private val mutableSelectedFile: MutableState<File>,
//  private val audioItemLoader: AudioItemLoader,
//  private val mutableSelectedAudioStream: MutableState<AudioStream>
) {
  val name: String = file.name
  val path: String = file.canonicalPath
  val key: String = file.canonicalPath

//  fun onSelect() {
//    CoroutineScopes.ioScope.launch {
//      mutableSelectedFile.value = file
//      mutableSelectedAudioStream.value = audioItemLoader.load(file.canonicalPath)
//      mutableSelectedAudioStream.value.pause()
//    }
//  }

  @Reusable
  class Factory @Inject constructor(
//    private val audioItemLoader: AudioItemLoader,
//    private val mutableSelectedAudioStream: MutableState<AudioStream>
  ) {
    fun create(file: File) =
      AudioTrackViewModel(file/*, mutableSelectedFile, audioItemLoader, mutableSelectedAudioStream*/)
  }
}