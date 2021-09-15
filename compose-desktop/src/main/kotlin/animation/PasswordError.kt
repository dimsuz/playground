package animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import kotlinx.coroutines.delay

@Composable
fun PasswordErrorAnimation() {
  Box(
    modifier = Modifier.size(300.dp),
    contentAlignment = Alignment.Center
  ) {
    var animateTrigger by remember { mutableStateOf(0) }
    val animatableOffset = remember { Animatable(initialValue = 0f) }
    val animationSpec = remember { tween<Float>(durationMillis = 150) }

    LaunchedEffect(animateTrigger) {
      animatableOffset.animateTo(targetValue = -30f, animationSpec)
      animatableOffset.animateTo(targetValue = 30f, animationSpec)
      animatableOffset.animateTo(targetValue = 0f, animationSpec)
      delay(200)
    }
    Button(modifier = Modifier.offset(x = animatableOffset.value.dp), onClick = { animateTrigger += 1 }) {
      Text("Button")
    }
  }
}
