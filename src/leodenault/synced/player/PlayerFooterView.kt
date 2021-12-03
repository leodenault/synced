package leodenault.synced.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PlayerFooter(
  modifier: Modifier = Modifier,
  isAudioPlaying: Boolean = false,
  activeTitle: String? = null,
  onPlayButtonClick: () -> Unit = {}
) {
  Column(
    modifier = modifier.width(IntrinsicSize.Max)
      .height(IntrinsicSize.Max)
      .background(color = MaterialTheme.colors.primary)
  ) {
    NowPlaying(title = activeTitle)
    PlayerControls(
      modifier = Modifier.fillMaxWidth().fillMaxHeight(),
      isAudioPlaying = isAudioPlaying,
      onPlayButtonClick = onPlayButtonClick
    )
  }
}
