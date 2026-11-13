package net.tubalcain.interfacesmultiplataforma.planazoApp

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BuscadorEventos(
    texto: String,
    onTextoChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = texto,
        onValueChange = onTextoChange,
        label = {
            Text("¿Qué evento buscas?")
        },
        modifier = modifier,
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White
        )
    )
}
