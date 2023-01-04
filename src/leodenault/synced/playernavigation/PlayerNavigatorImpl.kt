package leodenault.synced.playernavigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.github.kwhat.jnativehook.GlobalScreen
import dagger.Reusable
import leodenault.synced.app.PageView
import leodenault.synced.player.MediaKeyEventHandler
import leodenault.synced.player.PlayerPageView
import leodenault.synced.player.PlayerPageViewModel
import javax.inject.Inject

@Reusable
class PlayerNavigatorImpl @Inject constructor(
  private val playerPageViewModelFactory: PlayerPageViewModel.Factory
) : PlayerNavigator {
  private var mediaKeyEventHandler: MediaKeyEventHandler? = null

  override var currentPageView by mutableStateOf(PageView.EMPTY)
    private set

  override fun navigateToPlayer() {
    val playerPageViewModel = playerPageViewModelFactory.create()
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
