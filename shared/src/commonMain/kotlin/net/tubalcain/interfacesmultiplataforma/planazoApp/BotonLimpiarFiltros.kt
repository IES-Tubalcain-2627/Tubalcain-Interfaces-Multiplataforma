import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BotonLimpiarFiltros(
    onClick: () -> Unit
) {
    Button(onClick = onClick) {
        Text("Limpiar filtros")
    }
}
