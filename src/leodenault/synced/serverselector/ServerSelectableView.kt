package leodenault.synced.serverselector

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import leodenault.synced.ui.SelectableItem

@Composable
fun ServerSelectable(
  modifier: Modifier = Modifier,
  text: String,
  iconData: ImageBitmap? = null,
  isSelected: Boolean = false,
  onSelect: () -> Unit = {}
) {

  SelectableItem(
    modifier = modifier
      .fillMaxWidth()
      .height(IntrinsicSize.Min)
      .padding(5.dp),
    verticalAlignment = Alignment.CenterVertically,
    isSelected = isSelected,
    onSelect = onSelect
  ) {
    Column(
      modifier = Modifier.size(60.dp),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      if (iconData == null) {
        Column(
          modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colors.primary),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            modifier = modifier.width(IntrinsicSize.Min),
            text = text.first().toString(),
            color = MaterialTheme.colors.onPrimary,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
          )
        }
      } else {
        Image(
          bitmap = iconData,
          contentDescription = "Discord server icon."
        )
      }
    }

    Spacer(Modifier.width(2.dp).fillMaxHeight())
    Divider(
      color = MaterialTheme.colors.onBackground,
      modifier = Modifier.fillMaxHeight().width(1.dp)
    )
    Spacer(Modifier.width(2.dp).fillMaxHeight())

    Column(
      modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(MaterialTheme.colors.primary),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.Start
    ) {
      Text(text, modifier = Modifier.padding(all = 5.dp), color = MaterialTheme.colors.onPrimary)
    }
  }
}
