package leodenault.synced.logging

import dagger.Component
import java.util.logging.Handler
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    LoggingDaggerModule::class,
    LoggingDevDaggerModule::class
  ]
)
interface LoggingDevComponent {
  val loggingConfiguration: LoggingConfiguration
}