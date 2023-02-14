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

class PlayerPageView(private val viewModel: PlayerPageViewModel) : PageView {
  @Composable
  override fun render() {
    Column {
      PlayerHeader(viewModel.playerHeaderViewModel)

      Divider(modifier = Modifier.height(1.dp), color = MaterialTheme.colors.onBackground)

      AudioSelector(
        modifier = Modifier.fillMaxHeight(0.78f),
        viewModel = viewModel.audioSelectorViewModel
      )

      PlayerFooter(
        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
        isEnabled = viewModel.isChannelSelected.value,
        activeTitle = viewModel.activeTitle,
        isAudioPlaying = viewModel.isAudioPlaying,
        onPlayButtonClick = viewModel::onPlayCurrentTrack,
        onNextTrackClick = viewModel::onPlayNextTrack,
        onPreviousTrackClick = viewModel::onPlayPreviousTrack
      )
    }
  }
}
