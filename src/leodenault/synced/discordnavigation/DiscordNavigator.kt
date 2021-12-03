package leodenault.synced.discordnavigation

import leodenault.synced.navigation.Navigator
import leodenault.synced.discord.ConnectedClient
import leodenault.synced.discord.DisconnectedClient

interface DiscordNavigator: Navigator {
  fun navigateToLoadingPage(navigateToNextPage: suspend DiscordNavigator.() -> Unit)
  fun navigateToBotAccess(disconnectedClient: DisconnectedClient, errorMessage: String? = null)
  fun navigateToServerSelector(connectedClient: ConnectedClient)
  fun navigateToError(message: String)
}