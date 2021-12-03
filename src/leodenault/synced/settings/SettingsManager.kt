package leodenault.synced.settings

import leodenault.synced.settings.proto.SettingsProto.Settings
import java.util.prefs.Preferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsManager @Inject constructor(
  private val preferences: Preferences
) {
  private var settings: Settings? = null

  fun fetch(): Settings = settings ?: preferences.getByteArray(SETTINGS_KEY, null)?.let {
    Settings.parseFrom(it)
  } ?: Settings.getDefaultInstance()

  fun commit(settings: Settings) {
    this.settings = settings
    preferences.putByteArray(SETTINGS_KEY, settings.toByteArray())
    preferences.flush()
  }

  companion object {
    private const val SETTINGS_KEY = "settings"
  }
}
