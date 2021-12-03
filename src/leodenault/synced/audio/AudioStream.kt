package leodenault.synced.audio

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer

interface AudioStream {
  val title: String
  val isPaused: State<Boolean>

  fun read(): ByteArray?
  fun pause()
  fun resume()
  fun close()
}

internal fun audioStreamOf(audioPlayer: AudioPlayer) = object : AudioStream {
  override val title: String = audioPlayer.playingTrack?.identifier ?: ""

  override val isPaused = mutableStateOf(audioPlayer.isPaused)

  override fun read(): ByteArray? = audioPlayer.provide()?.data

  override fun pause() {
    audioPlayer.isPaused = true
    isPaused.value = true
  }

  override fun resume() {
    audioPlayer.isPaused = false
    isPaused.value = false
  }

  override fun close() = audioPlayer.stopTrack()
}
