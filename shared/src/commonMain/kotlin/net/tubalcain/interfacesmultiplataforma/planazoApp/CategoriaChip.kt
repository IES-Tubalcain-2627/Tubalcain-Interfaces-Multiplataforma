package net.tubalcain.interfacesmultiplataforma.planazoApp


import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CategoriaChip(
    categoria: String,
    seleccionada: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = seleccionada,
        onClick = onClick,
        label = {
            Text(categoria)
        },
        modifier = modifier
    )
}
