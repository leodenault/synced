package leodenault.synced.loadingpage

import dagger.Binds
import dagger.Module
import dagger.Provides
import leodenault.synced.navigation.Navigator
import leodenault.synced.util.Data
import leodenault.synced.util.MutableData
import leodenault.synced.util.mutableDataOf
import javax.inject.Singleton

@Module
abstract class LoadingPageDaggerModule {
  @Binds
  abstract fun bindNavigateToNextPage(
    impl: MutableData<suspend Navigator.() -> Unit>
  ): Data<suspend Navigator.() -> Unit>

  companion object {
    @Provides
    @Singleton
    fun provideNavigateToNextPage(): MutableData<suspend Navigator.() -> Unit> {
      return mutableDataOf { navigateToError("No page was provided for navigation.") }
    }
  }
}