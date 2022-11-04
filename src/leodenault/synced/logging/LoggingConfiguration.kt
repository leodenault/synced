package leodenault.synced.logging

import dagger.Reusable
import java.util.logging.Handler
import java.util.logging.LogManager
import java.util.logging.Logger
import java.util.logging.SimpleFormatter
import javax.inject.Inject

@Reusable
class LoggingConfiguration @Inject constructor(
  private val logHandlers: Set<@JvmSuppressWildcards Handler>
) {
  fun apply() {
    System.setProperty("java.util.logging.SimpleFormatter.format", "[%1\$tc] %4\$s: %5\$s%n")
    LogManager.getLogManager().reset()
    val logFormatter = SimpleFormatter()
    logHandlers.forEach {
      ROOT_LOGGER.addHandler(it.apply { formatter = logFormatter })
    }
  }

  companion object {
    private val ROOT_LOGGER = Logger.getLogger("")
  }
}