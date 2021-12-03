package leodenault.synced.settings

import java.io.File
import dagger.Module
import dagger.Provides
import java.util.prefs.Preferences

@Module
object SettingsDaggerModule {
  @Provides
  @SettingsFile
  fun provideSettingsFile() = File(".settings")

  @Provides
  fun provideAppPreferences() = Preferences.userRoot().node("leodenault/synced")
}