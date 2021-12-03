package leodenault.synced.error

import dagger.Binds
import dagger.Module
import dagger.Provides
import leodenault.synced.util.Data
import leodenault.synced.util.MutableData
import leodenault.synced.util.mutableDataOf
import javax.inject.Singleton

@Module
abstract class ErrorDaggerModule {
  @Binds
  @ErrorPageMessage
  abstract fun provideErrorMessage(@ErrorPageMessage impl: MutableData<String>): Data<String>

  companion object {
    @Provides
    @ErrorPageMessage
    @Singleton
    fun provideMutableErrorMessage(): MutableData<String> =
      mutableDataOf("No error message provided!")
  }
}