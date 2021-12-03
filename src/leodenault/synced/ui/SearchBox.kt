package leodenault.synced.ui

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchBox(
  modifier: Modifier = Modifier,
  value: String = "",
  placeholder: String = "",
  onValueChange: (String) -> Unit = {}
) {
  TextField(
    modifier = modifier,
    placeholder = { Text(text = placeholder) },
    value = value,
    leadingIcon = { Text(text = "\uD83D\uDD0D") },
    onValueChange = onValueChange,
    colors = TextFieldDefaults.textFieldColors(
      textColor = MaterialTheme.colors.onBackground,
      backgroundColor = MaterialTheme.colors.background
    ),
    shape = CircleShape
  )
}