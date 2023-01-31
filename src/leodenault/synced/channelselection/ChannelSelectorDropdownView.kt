package leodenault.synced.channelselection

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ChannelSelectorDropdown(
  modifier: Modifier = Modifier,
  viewModel: ChannelDropdownViewModel,
) {
  val viewState = viewModel.dropdownState
  Box(modifier = modifier) {
    ChannelSelectorButton(
      isLoading = viewModel.dropdownState is DropdownViewState.Loading,
      selectedChannel = viewState.selectedChannel,
      onClick = {
        if (viewState is DropdownViewState.Inactive) {
          viewModel.onViewStateChange(viewState.onOpenDropdown())
        }
      }
    )
    DropdownMenu(
      modifier = Modifier.width(IntrinsicSize.Max),
      expanded = viewState !is DropdownViewState.Inactive,
      onDismissRequest = { viewModel.onViewStateChange(viewState.onDropdownClosed()) },
    ) {
      when (viewState) {
        is DropdownViewState.SelectingServer -> {
          ServerDropdownCollection(
            viewState.serverList,
            viewState.selectedChannel?.server,
            onServerSelected = { viewModel.onViewStateChange(viewState.onSelectServer(it)) }
          )
        }

        is DropdownViewState.SelectingChannel -> {
          ChannelDropdownCollection(
            viewState.selectedServer,
            viewState.selectedChannel?.channel,
            onNavigateBack = { viewModel.onViewStateChange(viewState.onNavigateBack()) },
            onChannelSelected = {
              viewModel.onChannelSelected(it)
              viewModel.onViewStateChange(viewState.onChannelSelected(it))
            },
            onChannelDeselected = {
              viewModel.onChannelDeselected()
              viewModel.onViewStateChange(viewState.onChannelDeselected())
            }
          )
        }

        DropdownViewState.Loading, is DropdownViewState.Inactive -> {}
      }
    }
  }
}
