package leodenault.synced.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FileFinder(
  modifier: Modifier = Modifier,
  currentDirectory: FileDetails,
  directoryChildren: List<FileDetails>,
  onParentDirectorySelected: () -> Unit = {},
  onFileSelected: (FileDetails) -> Unit = {},
  onDrillDown: (FileDetails) -> Unit = {}
) {

  Column(modifier = modifier) {
    var selectedIndex by remember { mutableStateOf(-1) }

    Row(
      modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min)
        .background(MaterialTheme.colors.secondary).padding(bottom = 2.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Button(
        modifier = Modifier.padding(2.dp).width(IntrinsicSize.Min).height(IntrinsicSize.Min),
        onClick = {
          selectedIndex = -1
          onParentDirectorySelected()
        }
      ) {
        Text(text = "\uD83D\uDCC1\u2191", fontSize = 20.sp, softWrap = false)
      }
      Spacer(modifier = Modifier.width(5.dp))
      Text(
        modifier = Modifier
          .border(
            color = MaterialTheme.colors.onBackground,
            width = 1.dp,
            shape = MaterialTheme.shapes.small
          ).background(
            color = MaterialTheme.colors.background,
            shape = MaterialTheme.shapes.small
          ).padding(top = 2.dp, bottom = 2.dp, start = 4.dp, end = 4.dp)
          .fillMaxWidth(0.95f),
        text = currentDirectory.canonicalPath
      )
    }

    Divider(
      modifier = Modifier.height(1.dp),
      color = MaterialTheme.colors.onBackground
    )

    Row(modifier = Modifier.fillMaxWidth().height(300.dp)) {
      LoadingColumn(isLoading = false) {
        items(directoryChildren.size) { index ->
          SelectableItem(
            modifier = Modifier.fillParentMaxWidth(),
            isSelected = selectedIndex == index,
            onSelect = { selectedIndex = index },
            onDoubleTap = {
              onDrillDown(directoryChildren[index])
              selectedIndex = -1
            }
          ) {
            Text(text = directoryChildren[index].name)
          }
        }
      }
    }

    Divider(
      modifier = Modifier.height(1.dp),
      color = MaterialTheme.colors.onBackground
    )

    Row(
      modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min)
        .background(color = MaterialTheme.colors.secondary),
      horizontalArrangement = Arrangement.End
    ) {
      Button(
        modifier = Modifier.padding(2.dp),
        onClick = {
          onFileSelected(
            if (selectedIndex == -1) {
              currentDirectory
            } else {
              directoryChildren[selectedIndex]
            }
          )

        }
      ) {
        Text(text = "Select Directory")
      }
    }
  }
}

class FileDetails(val name: String, val canonicalPath: String)
