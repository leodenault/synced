package leodenault.synced.discordnavigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.Reusable
import leodenault.synced.app.PageView
import leodenault.synced.discord.ConnectedClient
import leodenault.synced.discord.DisconnectedClient
import leodenault.synced.error.ErrorPageView
import leodenault.synced.botaccess.BotAccessPageView
import leodenault.synced.botaccess.BotAccessPageViewModel
import leodenault.synced.loadingpage.LoadingPageView
import leodenault.synced.loadingpage.LoadingPageViewModel
import leodenault.synced.serverselector.ServerSelectorView
import leodenault.synced.serverselector.ServerSelectorViewModel
import leodenault.synced.settings.SettingsManager
import javax.inject.Inject

@Reusable
class DiscordNavigatorImpl @Inject constructor(
  private val serverSelectorViewModelFactory: ServerSelectorViewModel.Factory,
  private val settingsManager: SettingsManager
) : DiscordNavigator {

  override var currentPageView by mutableStateOf(PageView.EMPTY)
    private set

  override fun navigateToError(message: String) {
    currentPageView = ErrorPageView(message)
  }

  override fun navigateToBotAccess(
    disconnectedClient: DisconnectedClient,
    errorMessage: String?
  ) {
    currentPageView =
      BotAccessPageView(
        BotAccessPageViewModel(
          disconnectedClient,
          this,
          settingsManager,
          errorMessage
        )
      )
  }

  override fun navigateToLoadingPage(navigateToNextPage: suspend DiscordNavigator.() -> Unit) {
    currentPageView = LoadingPageView(LoadingPageViewModel(this, navigateToNextPage))
  }


  override fun navigateToServerSelector(connectedClient: ConnectedClient) {
    currentPageView =
      ServerSelectorView(
        serverSelectorViewModelFactory.create(connectedClient, this)
      )
  }
}