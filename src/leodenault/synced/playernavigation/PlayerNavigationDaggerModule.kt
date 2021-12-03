package leodenault.synced.playernavigation

import dagger.Binds
import dagger.Module

@Module
abstract class PlayerNavigationDaggerModule {
  @Binds
  abstract fun bindPlayerNavigator(impl: PlayerNavigatorImpl): PlayerNavigator
}