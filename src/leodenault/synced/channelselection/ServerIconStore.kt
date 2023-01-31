package leodenault.synced.channelselection

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.loadImageBitmap
import dev.kord.common.entity.Snowflake
import dev.kord.core.entity.Guild
import dev.kord.rest.Image
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import leodenault.synced.coroutines.CoroutineScopes
import java.io.ByteArrayInputStream
import java.net.URI
import java.util.*

object ServerIconStore {
  private val cachedIcons = mutableMapOf<Snowflake, Deferred<ImageBitmap?>>()

  suspend fun loadIconFor(server: Guild): Deferred<ImageBitmap?> =
    cachedIcons.computeIfAbsent(server.id) {
      CoroutineScopes.ioScope.async {
        val dataUri = server.getIcon(Image.Format.PNG)?.dataUri ?: return@async null
        val imageData = URI.create(dataUri).schemeSpecificPart.split(",").last()
        loadImageBitmap(
          ByteArrayInputStream(
            Base64.getDecoder().decode(imageData)
          )
        )
      }
    }

}