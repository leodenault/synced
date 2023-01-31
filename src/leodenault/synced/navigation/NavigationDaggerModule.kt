package leodenault.synced.navigation

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class NavigationDaggerModule {
  @Binds
  @Singleton
  abstract fun bindNavigator(impl: NavigatorImpl): Navigator
}