package leodenault.synced.botaccess

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import leodenault.synced.app.PageView

class BotAccessPageView(private val viewModel: BotAccessPageViewModel) : PageView {
  @Composable
  override fun render() {
    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      viewModel.errorMessage?.let {
        Row(
          modifier = Modifier.padding(bottom = 5.dp)
            .background(
              color = MaterialTheme.colors.error,
              shape = MaterialTheme.shapes.small
            ).padding(5.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          Text(text = it, color = MaterialTheme.colors.onError)
        }
      }

      TextField(
        modifier = Modifier.padding(bottom = 5.dp).fillMaxWidth(0.66f),
        value = viewModel.botToken,
        onValueChange = { viewModel.botToken = it },
        label = { Text(text = "Bot token") },
        enabled = !viewModel.isConnecting
      )

      if (viewModel.isConnecting) {
        CircularProgressIndicator()
      } else {
        Row(
          modifier = Modifier.padding(bottom = 5.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(text = "Remember bot token?")
          Switch(
            checked = viewModel.rememberBotToken,
            onCheckedChange = viewModel::onRememberBotTokenChange,
            colors = SwitchDefaults.colors(
              checkedThumbColor = MaterialTheme.colors.primary,
              checkedTrackColor = MaterialTheme.colors.primary
            )
          )
        }
        Button(
          onClick = viewModel::onConnectClick
        ) {
          Text(text = "Connect")
        }
      }
    }
  }
}
