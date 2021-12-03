package leodenault.synced.discord

import androidx.compose.runtime.State
import dagger.Reusable
import dev.kord.voice.AudioFrame
import dev.kord.voice.AudioProvider
import leodenault.synced.audio.AudioStream
import javax.inject.Inject

@Reusable
class AudioPlayer @Inject constructor(
  private val activeAudioStream: State<AudioStream?>
) : AudioProvider {
  override fun provide(): AudioFrame? =
    activeAudioStream.value?.read()?.let { AudioFrame.fromData(it) }
}