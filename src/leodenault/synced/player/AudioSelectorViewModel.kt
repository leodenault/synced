package leodenault.synced.player

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import leodenault.synced.audioselector.AudioTrackViewModel

class AudioSelectorViewModel(
    audioTrackState: State<List<AudioTrackViewModel>>
) {
    val audioTracks by audioTrackState

    val selectedAudioTrack: State<AudioTrackSelection?> = derivedStateOf {
        if (audioTracks.isEmpty()) return@derivedStateOf null

        val currentInternalSelectedTrack = internalSelectedTrack

        if (currentInternalSelectedTrack != null
            && indexFromKey(currentInternalSelectedTrack.track) > -1
        ) return@derivedStateOf internalSelectedTrack

        audioTracks.indexOfFirst { it.isSupported }.takeIf { it > -1 }
            ?.let { index -> AudioTrackSelection(audioTracks[index], index) }
    }

    private var internalSelectedTrack: AudioTrackSelection? by mutableStateOf(null)

    fun onSelect(audioTrack: AudioTrackViewModel) {
        internalSelectedTrack = AudioTrackSelection(audioTrack, indexFromKey(audioTrack))
    }

    /**
     * Returns the index of [audioTrack] by looking it up by its [key][AudioTrackViewModel.key]. If
     * no matching track is found, then -1 is returned.
     */
    private fun indexFromKey(audioTrack: AudioTrackViewModel): Int =
        audioTracks.indexOfFirst { it.key == audioTrack.key }

    class AudioTrackSelection(val track: AudioTrackViewModel, val index: Int)
}