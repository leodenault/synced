package leodenault.synced.serverselector

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import leodenault.synced.ui.PageHeader

@Composable
fun ServerSelectorHeader(
  modifier: Modifier = Modifier,
  isLoggingOut: Boolean,
  onLogoutClick: () -> Unit
) {
  PageHeader(modifier = modifier) {
    if (isLoggingOut) {
      CircularProgressIndicator()
    } else {
      Button(
        onClick = onLogoutClick,
        colors = ButtonDefaults.buttonColors(
          backgroundColor = MaterialTheme.colors.secondary,
          contentColor = MaterialTheme.colors.onSecondary
        )
      ) {
        Text(text = "Logout")
      }
    }
  }
}
