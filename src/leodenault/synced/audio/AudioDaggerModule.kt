package leodenault.synced.audio

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AudioDaggerModule {
  @Provides
  @Singleton
  fun provideAudioPlayerManager(): AudioPlayerManager {
    val audioPlayerManager = DefaultAudioPlayerManager().also {
      it.frameBufferDuration = 20 // ms
    }
    AudioSourceManagers.registerLocalSource(audioPlayerManager)
    return audioPlayerManager
  }

  @Provides
  @Singleton
  fun provideAudioPlayer(audioPlayerManager: AudioPlayerManager): AudioPlayer {
    return audioPlayerManager.createPlayer()
  }
}