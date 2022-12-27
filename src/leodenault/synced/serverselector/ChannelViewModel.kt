package leodenault.synced.serverselector

import dagger.Reusable
import dev.kord.core.behavior.channel.connect
import dev.kord.core.entity.channel.VoiceChannel
import dev.kord.voice.VoiceConnection
import kotlinx.coroutines.launch
import leodenault.synced.coroutines.CoroutineScopes
import leodenault.synced.discord.AudioPlayer
import leodenault.synced.util.MutableData
import javax.inject.Inject

@OptIn(dev.kord.common.annotation.KordVoice::class)
class ChannelViewModel private constructor(
  private val mutableVoiceConnection: MutableData<VoiceConnection?>,
  private val audioPlayer: AudioPlayer,
  private val channel: VoiceChannel,
) {
  val name = channel.name

  fun onChannelSelected() {
    CoroutineScopes.ioScope.launch {
      mutableVoiceConnection.value?.leave()
      mutableVoiceConnection.value = channel.connect { audioProvider = audioPlayer }
    }
  }

  fun onChannelLeave() {
    CoroutineScopes.ioScope.launch {
      mutableVoiceConnection.value?.leave()
      mutableVoiceConnection.value = null
    }
  }

  @Reusable
  class Factory @Inject constructor(
    private val mutableVoiceConnection: MutableData<VoiceConnection?>,
    private val audioPlayer: AudioPlayer
  ) {
    fun create(channel: VoiceChannel) =
      ChannelViewModel(mutableVoiceConnection, audioPlayer, channel)
  }
}