package leodenault.synced.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.github.kwhat.jnativehook.GlobalScreen
import leodenault.synced.app.PageView
import leodenault.synced.botaccess.BotAccessPageView
import leodenault.synced.botaccess.BotAccessPageViewModel
import leodenault.synced.discord.ConnectedClient
import leodenault.synced.discord.DisconnectedClient
import leodenault.synced.error.ErrorPageView
import leodenault.synced.loadingpage.LoadingPageView
import leodenault.synced.loadingpage.LoadingPageViewModel
import leodenault.synced.player.MediaKeyEventHandler
import leodenault.synced.player.PlayerPageView
import leodenault.synced.player.PlayerPageViewModel
import leodenault.synced.settings.SettingsManager
import javax.inject.Inject
import javax.inject.Singleton

class NavigatorImpl @Inject constructor(
  private val settingsManager: SettingsManager,
  private val playerPageViewModelFactory: PlayerPageViewModel.Factory
): Navigator {
  private var mediaKeyEventHandler: MediaKeyEventHandler? = null
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

  override fun navigateToLoadingPage(navigateToNextPage: suspend Navigator.() -> Unit) {
    currentPageView = LoadingPageView(LoadingPageViewModel(this, navigateToNextPage))
  }

  override fun navigateToPlayer(connectedClient: ConnectedClient) {
    val playerPageViewModel = playerPageViewModelFactory.create(connectedClient, this)
    currentPageView = PlayerPageView(playerPageViewModel)
    GlobalScreen.removeNativeKeyListener(mediaKeyEventHandler)
    mediaKeyEventHandler = MediaKeyEventHandler(
      playerPageViewModel::playCurrentTrack,
      playerPageViewModel::onPlayNextTrack,
      playerPageViewModel::onPlayPreviousTrack,
      playerPageViewModel::stopCurrentTrack
    )
    GlobalScreen.addNativeKeyListener(mediaKeyEventHandler)
  }
}