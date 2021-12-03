package leodenault.synced.coroutines

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

object CoroutineScopes {
  val mainScope = CoroutineScope(Dispatchers.Default + CoroutineName("Main Thread"))
  val viewModelScope = CoroutineScope(Dispatchers.Default + CoroutineName("View Model"))
  val ioScope = CoroutineScope(Dispatchers.IO + CoroutineName("IO"))
}