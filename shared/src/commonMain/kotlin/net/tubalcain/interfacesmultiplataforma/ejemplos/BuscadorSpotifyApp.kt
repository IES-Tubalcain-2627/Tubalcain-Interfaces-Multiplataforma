import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BuscadorSpotifyApp() {

    var nombre by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var cargando by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // 1. BOX RAÍZ: FONDO GRIS
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .background(Color(0xFFF1F5F9)),
        contentAlignment = Alignment.TopCenter
    ) {

        // 2. COLUMN: FONDO VERDE
        Column(
            modifier = Modifier
                .widthIn(max = 420.dp)
                .fillMaxSize()
                .background(Color(0xFF12C697))
                .padding(20.dp)
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Buscador Spotify",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 3. CARD: RESULTADO DE LA BÚSQUEDA
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Yellow
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = resultado.ifEmpty {
                            "Introduce un artista"
                        },
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 4. CAMPO DE BÚSQUEDA
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Artista musical") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                enabled = !cargando,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            // 5. ROW: BOTONES
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFD5D9FF))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Button(
                    enabled = !cargando,
                    onClick = {
                        nombre = ""
                        resultado = ""
                    }
                ) {
                    Text("Limpiar")
                }

                Button(
                    enabled = !cargando,
                    onClick = {
                        val artista = nombre.trim()

                        scope.launch {

                            cargando = true
                            resultado = ""

                            // SIMULAMOS LA BÚSQUEDA
                            delay(2000)

                            resultado = "Resultados: $artista"
                            cargando = false
                        }
                    }
                ) {
                    Text("Buscar")
                }
            }
        }

        // 6. LOADER CENTRADO EN PANTALLA
        if (cargando) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Color.Black.copy(alpha = 0.5f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = Color.White
                )
            }
        }
    }
}
