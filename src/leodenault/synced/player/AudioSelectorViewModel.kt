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

  fun onUnselect() {
    selectedTrack = null
  }

  fun onSelectNextTrack() = selectTrackOffsetBy(1)

  fun onSelectPreviousTrack() = selectTrackOffsetBy(-1)

  private fun selectTrackOffsetBy(offsetIndex: Int) {
    if (audioTracks.isEmpty()) return

    val selectedTrackIndex = indexFromKey(selectedTrack)
    var nextTrackIndex = selectedTrackIndex
    do {
      nextTrackIndex =
        Math.floorMod(nextTrackIndex + offsetIndex, audioTracks.size)
    } while (
      nextTrackIndex != selectedTrackIndex && !audioTracks[nextTrackIndex].isSupported)

    // Ignore if we've wrapped around the whole list.
    if (nextTrackIndex == selectedTrackIndex) return

    val newlySelectedTrack = audioTracks[nextTrackIndex]
    onSelect(newlySelectedTrack)
  }

  /**
   * Returns the index of [audioTrack] by looking it up by its [key][AudioTrackViewModel.key]. If
   * no matching track is found, then -1 is returned.
   */
  private fun indexFromKey(audioTrack: AudioTrackViewModel?): Int =
    if (audioTrack == null) -1 else audioTracks.indexOfFirst { it.key == audioTrack.key }
}