package leodenault.synced.player

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import leodenault.synced.audioselector.AudioTrackViewModel

class AudioSelectorViewModel(
  audioTrackState: State<List<AudioTrackViewModel>>,
  areTracksPlayable: State<Boolean>,
  private val onTrackSelected: (AudioTrackViewModel) -> Unit
) {
  val audioTracks by audioTrackState
  var selectedTrack: AudioTrackViewModel? by mutableStateOf(null)
    private set
  val areTracksPlayable by areTracksPlayable

  fun onSelect(audioTrack: AudioTrackViewModel) {
    selectedTrack = audioTrack
    onTrackSelected(audioTrack)
  }

  fun onSelectNextTrack() = selectTrackOffsetBy(1)

  fun onSelectPreviousTrack() = selectTrackOffsetBy(-1)

  private fun selectTrackOffsetBy(offsetIndex: Int) {
    val selectedTrackIndex = audioTracks.indexOf(selectedTrack)
    var nextTrackIndex = selectedTrackIndex
    do {
      nextTrackIndex =
        Math.floorMod(nextTrackIndex + offsetIndex, audioTracks.size)
    } while (
      nextTrackIndex != selectedTrackIndex && !audioTracks[nextTrackIndex].isSupported)

    val newlySelectedTrack = audioTracks[nextTrackIndex]
    onSelect(newlySelectedTrack)
  }
}