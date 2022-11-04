package leodenault.synced.logging

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import dagger.multibindings.Multibinds
import java.util.logging.*

@Module
abstract class LoggingDaggerModule {
  @Multibinds
  abstract fun loggingHandlers(): Set<Handler>

  companion object {
    @Provides
    @IntoSet
    fun provideInfoFileHandler(): Handler = FileHandler(
      /* pattern= */ "SEVERE-%u.log",
      /* limit= */ 50000,
      /* count= */ 1,
      /* append= */ false
    ).apply {
      level = Level.SEVERE
    }

    @Provides
    @IntoSet
    fun provideSevereFileHandler(): Handler = FileHandler(
      /* pattern= */ "INFO-%u.log",
      /* limit= */ 50000,
      /* count= */ 1,
      /* append= */ false
    ).apply {
      level = Level.INFO
    }
  }
}