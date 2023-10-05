package leodenault.synced.player

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun PlayerControls(
  modifier: Modifier = Modifier,
  isAudioPlaying: Boolean,
  onPlayButtonClick: () -> Unit = {},
  onNextTrackClick: () -> Unit = {},
  onPreviousTrackClick: () -> Unit = {}
) {
  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
  ) {
    val buttonModifier = Modifier.size(80.dp).padding(5.dp)
    val buttonColors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
    val buttonTint = MaterialTheme.colors.onSecondary

    Button(
      modifier = buttonModifier,
      onClick = onPreviousTrackClick,
      colors = buttonColors,
      shape = CircleShape
    ) {
      Icon(
        modifier = Modifier.size(24.dp),
        painter = painterResource("backward_arrows.svg"),
        contentDescription = "Previous track",
        tint = buttonTint
      )
    }
    Button(
      modifier = buttonModifier,
      onClick = onPlayButtonClick,
      colors = buttonColors,
      shape = CircleShape
    ) {
      Icon(
        modifier = Modifier.size(24.dp),
        painter = painterResource(
          if (isAudioPlaying) "double_rectangle.svg" else "forward_arrow.svg"
        ),
        contentDescription = "Play track",
        tint = buttonTint
      )
    }
    Button(
      modifier = buttonModifier,
      onClick = onNextTrackClick,
      colors = buttonColors,
      shape = CircleShape
    ) {
      Icon(
        modifier = Modifier.size(24.dp),
        painter = painterResource("forward_arrows.svg"),
        contentDescription = "Next track",
        tint = buttonTint
      )
    }
  }
}
