package net.tubalcain.interfacesmultiplataforma.ejemplos


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NombreUsuario() {

    var nombre by remember { mutableStateOf("") }

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Tu nombre") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Hola, $nombre")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { nombre = "" }
            ) {
                Text("Limpiar")
            }
        }
    }
}
