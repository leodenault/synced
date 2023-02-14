package leodenault.synced.player

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import leodenault.synced.audioselector.AudioTrack

@Composable
fun AudioSelector(
  modifier: Modifier = Modifier,
  viewModel: AudioSelectorViewModel
) {
  LazyColumn(modifier = modifier) {
    items(
      items = viewModel.audioTracks,
      key = { audioTrack -> audioTrack.key }
    ) { audioTrack ->
      AudioTrack(
        modifier = Modifier.height(40.dp),
        name = audioTrack.name,
        isSelected = viewModel.selectedTrack?.key == audioTrack.key,
        onSelect = { viewModel.onSelect(audioTrack) },
        isEnabled = audioTrack.isSupported,
        isPlayable = viewModel.areTracksPlayable
      )
    }
  }
}
