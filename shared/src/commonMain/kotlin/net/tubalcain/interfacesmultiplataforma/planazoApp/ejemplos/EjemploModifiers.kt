package net.tubalcain.interfacesmultiplataforma.planazoApp.ejemplos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun EjemploModifiers() {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Padding antes del fondo",
            color = Color.White,
            modifier = Modifier
                .padding(20.dp) //crea espacio exterior, alrededor del componente
                .background(Color.Magenta)
                .padding(16.dp) //crea espacio interior, dentro del componente
        )

        Text(
            text = "Fondo antes del padding",
            color = Color.White,
            modifier = Modifier
                .background(Color.Magenta)
                .padding(80.dp) //crea espacio interior, dentro del componente
        )
    }
}
