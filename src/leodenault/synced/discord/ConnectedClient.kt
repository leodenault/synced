package leodenault.synced.discord

import dev.kord.core.Kord
import dev.kord.core.entity.Guild
import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.core.on
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import leodenault.synced.coroutines.CoroutineScopes
import javax.inject.Inject
import javax.inject.Singleton

class ConnectedClient internal constructor(
  private val internalClient: Kord,
  private val disconnectedClient: DisconnectedClient
) {
  val servers: Flow<Guild> = internalClient.guilds

  suspend fun disconnect(): DisconnectedClient {
    internalClient.logout()
    internalClient.shutdown()
    return disconnectedClient
  }
}
