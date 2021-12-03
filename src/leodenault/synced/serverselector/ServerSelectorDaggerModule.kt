package leodenault.synced.serverselector

import dagger.Module
import dagger.Provides
import dev.kord.voice.VoiceConnection
import leodenault.synced.util.mutableDataOf
import javax.inject.Singleton

@Module
abstract class ServerSelectorDaggerModule {
  companion object {
    @Provides
    @Singleton
    fun provideVoiceConnection() = mutableDataOf<VoiceConnection?>(null)
  }
}
