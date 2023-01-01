package leodenault.synced.logging

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import java.util.logging.ConsoleHandler
import java.util.logging.Handler
import java.util.logging.Level

@Module
class LoggingDevDaggerModule {
  @Provides
  @IntoSet
  fun provideInfoConsoleHandler(): Handler = ConsoleHandler().apply {
    level = Level.ALL
  }
}