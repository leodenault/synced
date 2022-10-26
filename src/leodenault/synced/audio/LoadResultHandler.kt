package leodenault.synced.audio

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import kotlinx.coroutines.CompletableJob
import java.util.logging.Logger

internal class LoadResultHandler(
    private val audioPlayer: AudioPlayer, private val job: CompletableJob
) : AudioLoadResultHandler {

    override fun trackLoaded(audioTrack: AudioTrack) {
        audioPlayer.playTrack(audioTrack)
        job.complete()
    }

    override fun playlistLoaded(audioPlaylist: AudioPlaylist) {
        TODO("Not yet implemented")
    }

    override fun noMatches() {
        LOGGER.warning(
            "No match was found while loading audio because this feature does not exist yet."
        )
        job.complete()
    }

    override fun loadFailed(e: FriendlyException) {
        LOGGER.severe(
            "Failed to load audio due to the following reason: \"${e.message}\". Stack trace below"
        )
        e.printStackTrace()
        job.completeExceptionally(e)
    }

    companion object {
        private val LOGGER = Logger.getLogger("leodenault.synced.audio.LoadResultHandler")
    }
}
