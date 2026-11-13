package net.tubalcain.interfacesmultiplataforma.ejemplos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SaludosNuevo() {
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize().background(Color.Yellow),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Saludos!",
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(modifier = Modifier.height(14.dp))

                for (i in 1..10) {
                    Text("Alumno $i")
                }

                Spacer(modifier = Modifier.height(14.dp))

                Button(onClick = {
                    println("Descargando lista de alumnos...")
                }) {
                    Text("Descargar lista de alumnos")
                }
            }
        }
    }
}