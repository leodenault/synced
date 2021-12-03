package leodenault.synced.serverselector

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import dagger.Reusable
import dev.kord.common.entity.ChannelType
import dev.kord.core.entity.Guild
import dev.kord.core.entity.channel.VoiceChannel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import leodenault.synced.compose.collectOverTime
import leodenault.synced.compose.flowCollectionStateOf
import leodenault.synced.coroutines.CoroutineScopes
import leodenault.synced.discord.ConnectedClient
import leodenault.synced.discordnavigation.DiscordNavigator
import leodenault.synced.settings.SettingsManager
import leodenault.synced.util.MutableData
import javax.inject.Inject

class ServerSelectorViewModel private constructor(
  private val channelViewModelFactory: ChannelViewModel.Factory,
  private val discordNavigator: DiscordNavigator,
  private val connectedClient: ConnectedClient,
  private val mutableConnectedClient: MutableData<ConnectedClient?>,
  private val settingsManager: SettingsManager
) {
  val servers = flowCollectionStateOf<ServerViewModel>().also {
    it.collectOverTime(connectedClient.servers.map { server ->
      ServerViewModel(server) { onServerSelected(server) }
    }, CoroutineScopes.ioScope)
  }
  val channels = flowCollectionStateOf<ChannelViewModel>()
  var isLoggingOut by mutableStateOf(false)
    private set

  private fun onServerSelected(server: Guild) {
    val channelFlow =
      server.channels.filter { it.type == ChannelType.GuildVoice }.map { it as VoiceChannel }
        .map { channelViewModelFactory.create(it) }
    channels.collectOverTime(channelFlow, CoroutineScopes.ioScope)
  }

  fun onLogoutClick() {
    isLoggingOut = true
    CoroutineScopes.ioScope.launch {
      val disconnectedClient = connectedClient.disconnect()
      mutableConnectedClient.value = null
      settingsManager.commit(settingsManager.fetch().toBuilder().clearBotToken().build())
      discordNavigator.navigateToBotAccess(disconnectedClient)
    }
  }

  @Reusable
  class Factory @Inject constructor(
    private val channelViewModelFactory: ChannelViewModel.Factory,
    private val mutableConnectedClient: MutableData<ConnectedClient?>,
    private val settingsManager: SettingsManager
  ) {
    fun create(connectedClient: ConnectedClient, discordNavigator: DiscordNavigator) =
      ServerSelectorViewModel(
        channelViewModelFactory,
        discordNavigator,
        connectedClient,
        mutableConnectedClient,
        settingsManager
      )
  }
}