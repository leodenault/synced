package leodenault.synced.serverselector

import dev.kord.core.entity.Guild

class ServerViewModel(server: Guild, val onServerSelected: () -> Unit) {
  val name = server.name
}
