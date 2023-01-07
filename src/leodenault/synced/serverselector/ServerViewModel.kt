package leodenault.synced.serverselector

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import dev.kord.core.entity.Guild
import kotlinx.coroutines.launch
import leodenault.synced.coroutines.CoroutineScopes

class ServerViewModel(server: Guild, val onServerSelected: () -> Unit) {
  val name = server.name
  val icon: State<ImageBitmap?> = mutableStateOf<ImageBitmap?>(null).also {
    CoroutineScopes.viewModelScope.launch { it.value = ServerIconStore.loadIconFor(server).await() }
  }
}
