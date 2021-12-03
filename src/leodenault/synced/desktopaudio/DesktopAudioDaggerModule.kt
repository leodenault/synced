package leodenault.synced.desktopaudio

import dagger.Binds
import dagger.Module
import dagger.Provides
import leodenault.synced.util.Data
import leodenault.synced.util.MutableData
import leodenault.synced.util.mutableDataOf
import java.io.File
import javax.inject.Singleton

@Module
abstract class DesktopAudioDaggerModule {
  @Binds
  abstract fun bindTopLevelDirectory(impl: MutableData<File>): Data<File>

  companion object {
    @Provides
    @Singleton
    fun provideMutableTopLevelDirectory(): MutableData<File> =
      mutableDataOf(File("${System.getProperty("user.home")}/Music"))
  }
}