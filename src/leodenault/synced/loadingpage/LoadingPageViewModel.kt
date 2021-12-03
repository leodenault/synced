package leodenault.synced.loadingpage

import leodenault.synced.discordnavigation.DiscordNavigator
import javax.inject.Inject
import javax.inject.Singleton

class LoadingPageViewModel(
  private val discordNavigator: DiscordNavigator,
  private val navigateToNextPage: suspend DiscordNavigator.() -> Unit
) {
  suspend fun load() {
    discordNavigator.navigateToNextPage()
  }

  @Singleton
  class Factory @Inject constructor(private val discordNavigator: DiscordNavigator) {
    fun create(
      navigateToNextPage: suspend DiscordNavigator.() -> Unit
    ): LoadingPageViewModel =
      LoadingPageViewModel(discordNavigator, navigateToNextPage)
  }
}