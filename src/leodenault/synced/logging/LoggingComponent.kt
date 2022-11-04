package leodenault.synced.logging

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [LoggingDaggerModule::class])
interface LoggingComponent {
  val loggingConfiguration: LoggingConfiguration
}