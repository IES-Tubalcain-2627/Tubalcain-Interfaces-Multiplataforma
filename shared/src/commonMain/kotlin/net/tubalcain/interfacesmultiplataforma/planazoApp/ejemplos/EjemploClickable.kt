package net.tubalcain.interfacesmultiplataforma.planazoApp.ejemplos

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun EjemploClickable() {
    var pulsaciones by remember { mutableIntStateOf(0) }

    Text(
        text = "Pulsaciones: $pulsaciones",
        color = Color.White,
        modifier = Modifier
            .background(Color(0xFF7B2FF7))
            .clickable { pulsaciones++ }
            .padding(24.dp)
    )
}
