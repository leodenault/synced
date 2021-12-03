package leodenault.synced.desktopaudio

import dagger.Reusable
import leodenault.synced.settings.SettingsManager
import leodenault.synced.settings.proto.SettingsProto.Settings
import leodenault.synced.util.Data
import java.io.File
import javax.inject.Inject

@Reusable
class AudioDirectoryFile @Inject constructor(
  private val settingsManager: SettingsManager,
  private val audioDirectory: Data<File>
) {

  fun load(): File = settingsManager.fetch().takeIf { it.hasAudioDirectoryLocation() }
    ?.let { File(it.audioDirectoryLocation) }?.takeIf { it.exists() }
    ?: audioDirectory.value.also { setLocation(it) }

  fun setLocation(audioDirectory: File) = audioDirectory.takeIf { it.exists() }?.let {
    settingsManager.commit(
      Settings.newBuilder().setAudioDirectoryLocation(audioDirectory.canonicalPath)
        .build()
    )
  }
}