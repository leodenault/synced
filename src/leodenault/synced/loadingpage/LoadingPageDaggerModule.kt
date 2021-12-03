package leodenault.synced.loadingpage

import dagger.Binds
import dagger.Module
import dagger.Provides
import leodenault.synced.discordnavigation.DiscordNavigator
import leodenault.synced.util.Data
import leodenault.synced.util.MutableData
import leodenault.synced.util.mutableDataOf
import javax.inject.Singleton

@Module
abstract class LoadingPageDaggerModule {
  @Binds
  abstract fun bindNavigateToNextPage(
    impl: MutableData<suspend DiscordNavigator.() -> Unit>
  ): Data<suspend DiscordNavigator.() -> Unit>

  companion object {
    @Provides
    @Singleton
    fun provideNavigateToNextPage(): MutableData<suspend DiscordNavigator.() -> Unit> {
      return mutableDataOf { navigateToError("No page was provided for navigation.") }
    }
  }
}