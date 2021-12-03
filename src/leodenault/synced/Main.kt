package leodenault.synced

import leodenault.synced.app.DaggerApplicationComponent

fun main() {
  DaggerApplicationComponent.create().application.run()
}
