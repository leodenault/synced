package leodenault.synced.player

import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import leodenault.synced.audioselector.AudioTrack
import leodenault.synced.audioselector.AudioTrackViewModel
import leodenault.synced.ui.LoadingColumn

@Composable
fun AudioSelector(
  modifier: Modifier = Modifier,
  audioTracks: List<AudioTrackViewModel>,
  selectedTrack: AudioTrackViewModel?,
  onSelect: (AudioTrackViewModel) -> Unit,
  onDoubleTap: (AudioTrackViewModel) -> Unit
) {
  LoadingColumn(
    modifier = modifier,
    isLoading = false
  ) {
    items(
      items = audioTracks,
      key = { audioTrack -> audioTrack.key }
    ) {
      AudioTrack(
        name = it.name,
        isSelected = selectedTrack?.key == it.key,
        onSelect = { onSelect(it) },
        onDoubleTap = { onDoubleTap(it) }
      )
    }
  }
}
