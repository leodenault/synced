package leodenault.synced

import leodenault.synced.app.DaggerApplicationComponent
import leodenault.synced.logging.DaggerLoggingDevComponent

fun main() {
  DaggerLoggingDevComponent.create().loggingConfiguration.apply()
  DaggerApplicationComponent.create().application.run()
}
