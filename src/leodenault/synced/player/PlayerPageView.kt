package leodenault.synced.player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import leodenault.synced.app.PageView
import leodenault.synced.ui.PageHeader

class PlayerPageView(private val viewModel: PlayerPageViewModel) : PageView {
  @Composable
  override fun render(modifier: Modifier) {
    Column(modifier = modifier) {
      PlayerHeader(
        viewModel::onSelectDirectory,
        viewModel.searchBoxValue,
        viewModel::onSearchBoxChange
      )

      Divider(modifier = Modifier.height(1.dp), color = MaterialTheme.colors.onBackground)

      AudioSelector(
        modifier = Modifier.fillMaxHeight(0.78f),
        audioTracks = viewModel.audioSelectorViewModel.audioTracks,
        selectedTrack = viewModel.audioSelectorViewModel.selectedAudioTrack.value?.track,
        onSelect = viewModel.audioSelectorViewModel::onSelect,
        onDoubleTap = viewModel::onTrackDoubleTapped
      )

      PlayerFooter(
        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
        activeTitle = viewModel.activeTitle,
        isAudioPlaying = viewModel.isAudioPlaying,
        onPlayButtonClick = viewModel::onPlayButtonClick,
        onNextTrackClick = viewModel::onPlayNextTrack,
        onPreviousTrackClick = viewModel::onPlayPreviousTrack
      )
    }
  }
}
