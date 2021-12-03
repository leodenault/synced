package leodenault.synced.audio

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager
import dagger.Reusable
import kotlinx.coroutines.Job
import javax.inject.Inject

@Reusable
class AudioItemLoader @Inject constructor(
  private val audioPlayerManager: AudioPlayerManager,
  private val audioPlayer: AudioPlayer
) {
  suspend fun load(trackLocation: String): AudioStream {
    val job = Job()
    audioPlayerManager.loadItem(trackLocation, LoadResultHandler(audioPlayer, job))
    job.join()

    return audioStreamOf(audioPlayer)
  }
}
