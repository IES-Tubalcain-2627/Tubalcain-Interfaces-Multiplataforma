package net.tubalcain.interfacesmultiplataforma.planazoApp.ejemplos

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun EjemploCombinedClickable() {
    var mensaje by remember { mutableStateOf("Pulsa sobre la tarjeta") }

    Text(
        text = mensaje,
        color = Color.White,
        modifier = Modifier
            .background(Color(0xFF252535))
            .combinedClickable(
                onClick = {
                    mensaje = "Pulsación normal"
                },
                onLongClick = {
                    mensaje = "Pulsación larga"
                }
            )
            .padding(24.dp)
    )
}
