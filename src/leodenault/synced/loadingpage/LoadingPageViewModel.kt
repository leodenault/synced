package leodenault.synced.loadingpage

import leodenault.synced.navigation.Navigator
import javax.inject.Inject
import javax.inject.Singleton

class LoadingPageViewModel(
  private val navigator: Navigator,
  private val navigateToNextPage: suspend Navigator.() -> Unit
) {
  suspend fun load() {
    navigator.navigateToNextPage()
  }

  @Singleton
  class Factory @Inject constructor(private val navigator: Navigator) {
    fun create(
      navigateToNextPage: suspend Navigator.() -> Unit
    ): LoadingPageViewModel =
      LoadingPageViewModel(navigator, navigateToNextPage)
  }
}