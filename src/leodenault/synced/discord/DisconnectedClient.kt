package leodenault.synced.discord

import dev.kord.core.Kord
import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.core.on
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import leodenault.synced.coroutines.CoroutineScopes
import leodenault.synced.util.MutableData
import javax.inject.Inject
import javax.inject.Singleton

class DisconnectedClient private constructor(
  private val mutableConnectedClient: MutableData<ConnectedClient?>
) {
  suspend fun connect(botToken: String): ClientConnectionResult =
    runCatching { Kord(botToken) }.fold(
      onFailure = { FailedClientConnectionResult(it) },
      onSuccess = { internalClient ->
        val waitForConnection = Job()

        internalClient.on<ReadyEvent> {
          waitForConnection.complete()
        }

        CoroutineScopes.ioScope.launch { internalClient.login() }
        waitForConnection.join()

        SuccessfulClientConnectionResult(ConnectedClient(internalClient, this).also {
          mutableConnectedClient.value = it
        })
      }
    )

  @Singleton
  class Factory @Inject constructor(
    private val mutableConnectedClient: MutableData<ConnectedClient?>
  ) {

    fun create() = DisconnectedClient(mutableConnectedClient)
  }
}

sealed class ClientConnectionResult {
  abstract fun <R> fold(onSuccess: (ConnectedClient) -> R, onFailure: (Throwable) -> R): R
}

private class FailedClientConnectionResult(
  private val throwable: Throwable
) : ClientConnectionResult() {
  override fun <R> fold(
    onSuccess: (ConnectedClient) -> R, onFailure: (Throwable) -> R
  ) = onFailure(throwable)
}

private class SuccessfulClientConnectionResult(
  private val connectedClient: ConnectedClient
) : ClientConnectionResult() {
  override fun <R> fold(
    onSuccess: (ConnectedClient) -> R, onFailure: (Throwable) -> R
  ) = onSuccess(connectedClient)
}
