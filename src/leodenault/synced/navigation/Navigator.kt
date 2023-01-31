package leodenault.synced.navigation

import leodenault.synced.app.PageView
import leodenault.synced.discord.ConnectedClient
import leodenault.synced.discord.DisconnectedClient

interface Navigator {
  val currentPageView: PageView

  fun navigateToError(message: String)
  fun navigateToBotAccess(
    disconnectedClient: DisconnectedClient,
    errorMessage: String? = null
  )

  fun navigateToLoadingPage(navigateToNextPage: suspend Navigator.() -> Unit)
  fun navigateToPlayer(connectedClient: ConnectedClient)
}