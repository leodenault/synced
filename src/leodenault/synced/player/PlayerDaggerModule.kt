package leodenault.synced.player

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import dagger.Binds
import dagger.Module
import dagger.Provides
import leodenault.synced.desktopaudio.AudioFile
import javax.inject.Singleton

@Module
abstract class PlayerDaggerModule {
    @Binds
    abstract fun bindAudioFiles(impl: MutableState<List<AudioFile>>): State<List<AudioFile>>

    companion object {
        @Provides
        @Singleton
        fun provideMutableAudioFiles(): MutableState<List<AudioFile>> = mutableStateOf(emptyList())
    }
}