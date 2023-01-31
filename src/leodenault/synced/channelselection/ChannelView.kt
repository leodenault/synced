package leodenault.synced.channelselection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Channel(
  modifier: Modifier = Modifier,
  name: String,
  ctaText: String,
  onCtaClick: () -> Unit = {},
  channelColors: @Composable MutableChannelColors.() -> Unit = {}
) {
  val colors = MutableChannelColors().apply(channelColors)
  Row(
    modifier = modifier.height(IntrinsicSize.Min)
      .fillMaxWidth()
      .background(color = colors.background)
      .padding(5.dp),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Column(
      modifier = Modifier.fillMaxHeight(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.Start
    ) {
      Text(
        modifier = Modifier.padding(5.dp),
        text = name,
        color = colors.text
      )
    }
    Column(
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.End
    ) {
      Button(
        modifier = Modifier.padding(5.dp),
        colors = ButtonDefaults.buttonColors(
          backgroundColor = colors.buttonColors.background
        ),
        onClick = onCtaClick
      ) {
        Text(text = ctaText, color = colors.buttonColors.text)
      }
    }
  }
}

//class ChannelColors(
//  val background: Color,
//  val text: Color,
//  val buttonColors: ButtonColors
//) {
//  class ButtonColors(
//    val background: Color,
//    val text: Color
//  )
//}

fun interface Builder<T> {
  @Composable
  fun build(): T
}

interface ChannelColors {
  val background: Color
  val text: Color
  val buttonColors: ButtonColors

  interface ButtonColors {
    val background: Color
    val text: Color
  }
}

class MutableChannelColors internal constructor() : Builder<ChannelColors> {
  var background: Color? = null
  var text: Color? = null
  var buttonColors: @Composable (MutableButtonColors.() -> Unit) = {}

  @Composable
  override fun build() = object : ChannelColors {
    override val background: Color =
      this@MutableChannelColors.background ?: MaterialTheme.colors.background
    override val text: Color = this@MutableChannelColors.text ?: MaterialTheme.colors.onBackground
    override val buttonColors: ChannelColors.ButtonColors =
      MutableButtonColors().apply(this@MutableChannelColors.buttonColors)
  }

  class MutableButtonColors internal constructor() : Builder<ChannelColors.ButtonColors> {
    var background: Color? = null
    var text: Color? = null

    @Composable
    override fun build() = object : ChannelColors.ButtonColors {
      override val background: Color =
        this@MutableButtonColors.background ?: MaterialTheme.colors.primary
      override val text: Color = this@MutableButtonColors.text ?: MaterialTheme.colors.onPrimary

    }
  }
}


@Composable
fun <T : Builder<U>, U> T.apply(config: @Composable T.() -> Unit): U {
  config(this)
  return this.build()
}
