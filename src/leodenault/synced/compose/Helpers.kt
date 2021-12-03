package leodenault.synced.compose

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach

class FlowCollectionData<T> internal constructor(
  internal val internalContents: SnapshotStateList<T>,
  internal var internalIsLoading: MutableState<Boolean>
) {
  val contents: List<T> = internalContents
  val isLoading: Boolean by internalIsLoading
}

fun <T> flowCollectionStateOf(
  vararg items: T,
  isLoading: Boolean = true
): FlowCollectionData<T> {
  val initialContentState = mutableStateListOf(*items)
  val initialLoadingState = mutableStateOf(isLoading)
  return FlowCollectionData(
    internalContents = initialContentState,
    internalIsLoading = initialLoadingState
  )
}

fun <T> FlowCollectionData<T>.collectOverTime(
  flow: Flow<T>,
  coroutineScope: CoroutineScope
) {
  flow.onEach { internalContents.add(it) }.onCompletion {
    internalIsLoading.value = false
  }.launchIn(coroutineScope)
}
