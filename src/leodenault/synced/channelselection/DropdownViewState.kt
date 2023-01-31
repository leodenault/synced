package leodenault.synced.channelselection

import androidx.compose.ui.graphics.ImageBitmap
import dev.kord.core.entity.channel.VoiceChannel
import java.lang.UnsupportedOperationException

sealed interface DropdownViewState {
  val selectedChannel: ChannelData?
  fun onDropdownClosed(): Inactive

  object Loading : DropdownViewState {
    override val selectedChannel: ChannelData? = null
    override fun onDropdownClosed(): Nothing =
      throw UnsupportedOperationException("Dropdown cannot be closed in loading state.")
  }

  class Inactive constructor(
    private val serverList: List<ServerData>,
    override val selectedChannel: ChannelData?
  ) : DropdownViewState {
    fun onOpenDropdown(): DropdownViewState = if (selectedChannel == null) {
      SelectingServer(serverList, null)
    } else {
      SelectingChannel(
        serverList,
        selectedServer = selectedChannel.server,
        selectedChannel = selectedChannel
      )
    }

    override fun onDropdownClosed() = this
  }

  class SelectingServer(
    val serverList: List<ServerData>,
    override val selectedChannel: ChannelData?
  ) : DropdownViewState {
    fun onSelectServer(selectedServer: ServerData): DropdownViewState = SelectingChannel(
      serverList,
      selectedServer,
      selectedChannel
    )

    override fun onDropdownClosed() = Inactive(serverList, selectedChannel)
  }

  class SelectingChannel(
    private val serverList: List<ServerData>,
    val selectedServer: ServerData,
    override val selectedChannel: ChannelData?
  ) : DropdownViewState {
    override fun onDropdownClosed() = Inactive(serverList, selectedChannel)

    fun onNavigateBack() = SelectingServer(serverList, selectedChannel)

    fun onChannelSelected(selectedChannel: ChannelData) = Inactive(serverList, selectedChannel)

    fun onChannelDeselected() = Inactive(serverList, null)
  }

  class ChannelData(val server: ServerData, val channel: VoiceChannel)

  sealed interface ServerData {
    val name: String
    val channels: List<VoiceChannel>

    fun withIcon(iconData: ImageBitmap) = WithIcon(this, iconData)

    class WithIcon(serverData: ServerData, val iconData: ImageBitmap) : ServerData by serverData

    private class Impl(
      override val name: String,
      override val channels: List<VoiceChannel>
    ) : ServerData

    companion object {
      fun of(name: String, channels: List<VoiceChannel>): ServerData = Impl(name, channels)
    }
  }
}
