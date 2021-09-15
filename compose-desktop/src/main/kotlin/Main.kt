// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import animation.PasswordErrorAnimation

fun main() = application {
  Window(
    onCloseRequest = { exitApplication() },
    title = "Compose Playground"
  ) {
    MaterialTheme {
      PasswordErrorAnimation()
    }
  }
}
