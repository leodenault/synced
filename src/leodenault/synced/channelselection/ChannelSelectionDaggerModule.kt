package leodenault.synced.channelselection

import dagger.Module
import dagger.Provides
import dev.kord.voice.VoiceConnection
import leodenault.synced.util.mutableDataOf
import javax.inject.Singleton

@Module
abstract class ChannelSelectionDaggerModule {
  companion object {
    @Provides
    @Singleton
    fun provideVoiceConnection() = mutableDataOf<VoiceConnection?>(null)
  }
}
