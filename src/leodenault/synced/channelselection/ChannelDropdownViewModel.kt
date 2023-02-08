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
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
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
  var previousVoiceConnectJob: Job? = null
  var currentVoiceConnectJob: Job? = null

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

  fun onChannelSelected(
    channelData: DropdownViewState.ChannelData,
    viewState: DropdownViewState.SelectingChannel
  ) {
    previousVoiceConnectJob = currentVoiceConnectJob
    currentVoiceConnectJob = CoroutineScopes.ioScope.launch {
      previousVoiceConnectJob?.cancelAndJoin()
      if (mutableVoiceConnection.value != null) {
        disconnectChannel(viewState)
        // TODO(#11): Remove this delay once the issue is fixed.
        delay(250)
      }

      mutableVoiceConnection.value = channelData.channel.connect { audioProvider = audioPlayer }
      onChannelSelectedListeners.forEach { it() }
      onViewStateChange(viewState.onChannelSelected(channelData))
    }.also { currentVoiceConnectJob = it }
  }

  fun onChannelDeselected(viewState: DropdownViewState.SelectingChannel) {
    CoroutineScopes.ioScope.launch { disconnectChannel(viewState) }
  }

  private suspend fun disconnectChannel(viewState: DropdownViewState.SelectingChannel) {
    mutableVoiceConnection.value?.leave()
    mutableVoiceConnection.value = null

    onChannelDeselectedListeners.forEach { it() }
    onViewStateChange(viewState.onChannelDeselected())
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