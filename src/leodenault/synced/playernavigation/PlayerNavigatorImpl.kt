package leodenault.synced.playernavigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.Reusable
import leodenault.synced.app.PageView
import leodenault.synced.player.PlayerPageView
import leodenault.synced.player.PlayerPageViewModel
import javax.inject.Inject

@Reusable
class PlayerNavigatorImpl @Inject constructor(
  private val playerPageViewModelFactory: PlayerPageViewModel.Factory
) : PlayerNavigator {
  override var currentPageView by mutableStateOf(PageView.EMPTY)
    private set

  override fun navigateToPlayer() {
    currentPageView = PlayerPageView(playerPageViewModelFactory.create())
  }
}