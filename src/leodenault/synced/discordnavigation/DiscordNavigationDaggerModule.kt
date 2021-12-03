package leodenault.synced.discordnavigation

import dagger.Binds
import dagger.Module

@Module
abstract class DiscordNavigationDaggerModule {
  @Binds
  abstract fun bindNavigator(impl: DiscordNavigatorImpl): DiscordNavigator
}