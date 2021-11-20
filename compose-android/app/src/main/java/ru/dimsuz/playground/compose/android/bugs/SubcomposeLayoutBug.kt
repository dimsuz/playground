package ru.dimsuz.playground.compose.android.bugs

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.dp

@Composable
fun SubComposeLayoutBug() {
  Column(modifier = Modifier.padding(horizontal = 24.dp)) {
    var value by remember { mutableStateOf("foo") }
    ContentLayout(
      topSection = {
        Spacer(modifier = Modifier.height(if (value == "foo") 48.dp else 72.dp))
      },
      bottomSection = { topSectionHeight ->
        BottomSection(value = value, topSectionHeight = topSectionHeight)
      }
    )

    Button(
      onClick = { value = if (value == "foo") "bar" else "foo" },
      content = { Text("Toggle") }
    )
  }
}

@Composable
private fun BottomSection(
  value: String,
  topSectionHeight: Int,
) {
  println("called with value=$value, topSectionHeight=$topSectionHeight")
  BoxWithConstraints {
    Column {
      WrappedText(value)
      Text(value)
    }
  }
}

@Composable
private fun WrappedText(value: String) {
  Text(text = value)
}

@Composable
fun ContentLayout(
  modifier: Modifier = Modifier,
  topSection: @Composable () -> Unit,
  bottomSection: @Composable (topSectionHeight: Int) -> Unit,
) {
  SubcomposeLayout(modifier = modifier) { constraints ->
    val topPlaceables = subcompose(SlotId.TopSection, topSection)
      .map { it.measure(constraints.copy(minHeight = 0)) }

    val topSectionHeight = topPlaceables.sumOf { it.height }

    val bottomPlaceables = subcompose( SlotId.BottomSection, content = { bottomSection(topSectionHeight) })
      .map { it.measure(constraints.copy(minHeight = 0)) }

    val bottomSectionHeight = bottomPlaceables.sumOf { it.height }

    layout(constraints.maxWidth, topSectionHeight + bottomSectionHeight) {
      topPlaceables.forEach { it.placeRelative(x = 0, y = 0) }
      bottomPlaceables.forEach { it.placeRelative(x = 0, y = topSectionHeight) }
    }
  }
}

enum class SlotId { TopSection, BottomSection }

