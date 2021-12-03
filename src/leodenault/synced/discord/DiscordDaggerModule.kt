package leodenault.synced.discord

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import dagger.Binds
import dagger.Module
import dagger.Provides
import leodenault.synced.audio.AudioStream
import leodenault.synced.util.Data
import leodenault.synced.util.MutableData
import leodenault.synced.util.mutableDataOf
import javax.inject.Singleton

@Module
abstract class DiscordDaggerModule {
  @Binds
  abstract fun bindConnectedClient(
    impl: MutableData<ConnectedClient?>
  ): Data<ConnectedClient?>

  @Binds
  abstract fun bindSelectedAudioStream(
    impl: MutableState<@JvmWildcard AudioStream?>
  ): State<@JvmWildcard AudioStream?>

  companion object {
    @Provides
    @Singleton
    fun provideMutableConnectedClient(): MutableData<ConnectedClient?> {
      return mutableDataOf(null)
    }

    @Provides
    @Singleton
    fun provideMutableSelectedAudioStream(): MutableState<@JvmWildcard AudioStream?> =
      mutableStateOf(null)
  }
}