package net.tubalcain.interfacesmultiplataforma.planazoApp

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color


@Composable
fun EjemploAnimacion() {
    var activado by remember {
        mutableStateOf(false)
    }

    val color by animateColorAsState(
        targetValue = if (activado)
            Color.Magenta
        else
            Color.DarkGray,
        animationSpec = tween(1000),
        label = "color"
    )

    Button(
        onClick = { activado = !activado },
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        )
    ) {
        Text("Cambiar color")
    }
}
