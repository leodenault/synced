package leodenault.synced.audio

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import kotlinx.coroutines.CompletableJob

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
    println("Could not find a match.")
    job.complete()
  }

  override fun loadFailed(e: FriendlyException) {
    println(e.message)
    e.printStackTrace()
    job.completeExceptionally(e)
  }
}
