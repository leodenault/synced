package leodenault.synced

import leodenault.synced.app.DaggerApplicationComponent
import leodenault.synced.logging.DaggerLoggingComponent

fun main() {
  DaggerLoggingComponent.create().loggingConfiguration.apply()
  DaggerApplicationComponent.create().application.run()
}
