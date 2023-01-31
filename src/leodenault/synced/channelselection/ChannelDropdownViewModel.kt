package leodenault.synced.channelselection

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.Reusable
import dev.kord.common.entity.ChannelType
import dev.kord.core.behavior.channel.asChannelOf
import dev.kord.core.behavior.channel.connect
import dev.kord.core.entity.channel.VoiceChannel
import dev.kord.voice.VoiceConnection
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.launch
import leodenault.synced.coroutines.CoroutineScopes
import leodenault.synced.discord.AudioPlayer
import leodenault.synced.discord.ConnectedClient
import leodenault.synced.channelselection.DropdownViewState.ServerData
import leodenault.synced.util.MutableData
import javax.inject.Inject

class ChannelDropdownViewModel private constructor(
  connectedClient: ConnectedClient,
  private val audioPlayer: AudioPlayer,
  private val mutableVoiceConnection: MutableData<VoiceConnection?>,
  private val onChannelSelectedListeners: List<() -> Unit>,
  private val onChannelDeselectedListeners: List<() -> Unit>
) {
  var dropdownState: DropdownViewState by mutableStateOf(DropdownViewState.Loading)
    private set

  init {
    CoroutineScopes.ioScope.launch {
      val serverData =
        connectedClient.servers.fold(mutableListOf<ServerData>()) { serverList, server ->
          var serverData = ServerData.of(
            server.name,
            server.channels.filter {
              it.type == ChannelType.GuildVoice
            }.fold(mutableListOf()) { channelNames, channel ->
              channelNames.add(channel.asChannelOf<VoiceChannel>())
              channelNames
            })
          ServerIconStore.loadIconFor(server).await()
            ?.let { serverData = serverData.withIcon(it) }
          serverList.add(serverData)
          serverList
        }
      dropdownState = DropdownViewState.Inactive(serverData, null)
    }
  }

  fun onViewStateChange(newState: DropdownViewState) {
    dropdownState = newState
  }

  fun onChannelSelected(channelData: DropdownViewState.ChannelData) {
    if (mutableVoiceConnection.value != null) {
      onChannelDeselected()
    }

    CoroutineScopes.ioScope.launch {
      mutableVoiceConnection.value = channelData.channel.connect { audioProvider = audioPlayer }
    }
    onChannelSelectedListeners.forEach { it() }
  }

  fun onChannelDeselected() {
    CoroutineScopes.ioScope.launch {
      mutableVoiceConnection.value?.leave()
      mutableVoiceConnection.value = null
    }
    onChannelDeselectedListeners.forEach { it() }
  }

  @Reusable
  class Factory @Inject constructor(
    private val audioPlayer: AudioPlayer,
    private val mutableVoiceConnection: MutableData<VoiceConnection?>
  ) {
    fun create(
      connectedClient: ConnectedClient,
      onChannelSelectedListeners: List<() -> Unit>,
      onChannelDeselectedListeners: List<() -> Unit>
    ) = ChannelDropdownViewModel(
      connectedClient,
      audioPlayer,
      mutableVoiceConnection,
      onChannelSelectedListeners,
      onChannelDeselectedListeners
    )
  }
}