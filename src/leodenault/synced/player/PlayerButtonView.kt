package leodenault.synced.player

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PlayerButton(text: String, onClick: () -> Unit) {
    Button(
        modifier = Modifier.size(80.dp).padding(5.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
        shape = CircleShape
    ) {
        Text(
            text = text,
            fontSize = 30.sp,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center
        )
    }
}
