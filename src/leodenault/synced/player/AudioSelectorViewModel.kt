package leodenault.synced.player

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import leodenault.synced.audioselector.AudioTrackViewModel

class AudioSelectorViewModel(
  audioTrackState: State<List<AudioTrackViewModel>>
) {
  val audioTracks by audioTrackState

  var selectedAudioTrack by mutableStateOf<AudioTrackViewModel?>(null)
    private set

  fun onSelect(audioTrack: AudioTrackViewModel) {
    selectedAudioTrack = audioTrack
  }
}