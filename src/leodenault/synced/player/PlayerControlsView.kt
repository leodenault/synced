package leodenault.synced.player

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
        PlayerButton("\u23EE", onPreviousTrackClick)
        PlayerButton(if (isAudioPlaying) "\u23F8" else "\u25B6", onPlayButtonClick)
        PlayerButton("\u23ED", onNextTrackClick)
    }
}
