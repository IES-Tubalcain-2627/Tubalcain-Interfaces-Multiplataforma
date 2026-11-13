
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.json.*

// COLORES
private val Fondo = Color(0xFF121212)
private val Tarjeta = Color(0xFF242424)
private val Verde = Color(0xFF1DB954)
private val Gris = Color(0xFFB3B3B3)

// MODELO DE DATOS
data class Artista(
    val nombre: String,
    val spotifyId: String,
    val imagenUrl: String? = null
) {
    val spotifyUrl: String
        get() = "https://open.spotify.com/artist/$spotifyId"
}

@Composable
fun SpotifyCloneApp() {

    val navegador = LocalUriHandler.current

    var busqueda by remember { mutableStateOf("") }

    // ARTISTAS
    val artistasBase = remember {
        listOf(
            Artista("Quevedo", "52iwsT98xCoGgiGntTiR7K"),
            Artista("Bad Bunny", "4q3ewBCX7sLwd24euuV69X"),
            Artista("Lucho RK", "1y6tVxTqgNfqxTayfohSKJ"),
            Artista("La Pantera", "0IEzMvarfVycBJAXjjEZOL"),
            Artista("JC Reyes", "0FwnPHExlRRxEZPLAi5tmG"),
            Artista("Rvfv", "2CCgb0KApjfQDuTppovpf8"),
            Artista("Nirvana", "6olE6TJLqED3rqDCT0FyPh"),
            Artista("Linkin Park", "6XyY86QOPPrYVGvF9ch6wz"),
            Artista("Red Hot Chili Peppers", "0L8ExT028jH3ddEcZwqJJ5")
        )
    }

    var artistas by remember {
        mutableStateOf(artistasBase)
    }

    val cliente = remember { HttpClient() }

    DisposableEffect(cliente) {
        onDispose { cliente.close() }
    }

    // CARGA DE IMÁGENES DESDE SPOTIFY
    LaunchedEffect(Unit) {
        artistas = artistasBase.map { artista ->
            try {
                val respuesta = cliente.get(
                    "https://open.spotify.com/oembed"
                ) {
                    parameter("url", artista.spotifyUrl)
                }

                val json = Json.parseToJsonElement(
                    respuesta.bodyAsText()
                ).jsonObject

                val imagen = json["thumbnail_url"]
                    ?.jsonPrimitive
                    ?.contentOrNull

                artista.copy(imagenUrl = imagen)

            } catch (e: CancellationException) {
                throw e
            } catch (_: Exception) {
                artista
            }
        }
    }

    // FILTRADO DE ARTISTAS
    val artistasFiltrados = artistas.filter {
        it.nombre.contains(busqueda, ignoreCase = true)
    }

    MaterialTheme(
        colorScheme = darkColorScheme(primary = Verde)
    ) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Fondo,
            contentColor = Color.White
        ) {

            BoxWithConstraints(
                modifier = Modifier.fillMaxSize()
            ) {

                val esPantallaGrande = maxWidth >= 800.dp

                // SCROLL EN TO_DO EL ANCHO DE LA PANTALLA
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding(),
                    contentPadding = PaddingValues(
                        horizontal = if (esPantallaGrande) 32.dp else 16.dp,
                        vertical = 24.dp
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {

                    // 1. CABECERA
                    item {
                        Row(
                            modifier = Modifier
                                .widthIn(max = 1100.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Column {
                                Text(
                                    text = "Música DAM2",
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )

                                Text(
                                    text = "Descubre tus artistas",
                                    fontSize = 16.sp,
                                    color = Gris
                                )
                            }

                            Text(
                                text = "♫",
                                fontSize = 36.sp,
                                color = Verde
                            )
                        }
                    }

                    // 2. BUSCADOR
                    item {
                        Box(
                            modifier = Modifier
                                .widthIn(max = 1100.dp)
                                .fillMaxWidth()
                        ) {
                            OutlinedTextField(
                                value = busqueda,
                                onValueChange = { busqueda = it },
                                placeholder = {
                                    Text(
                                        "Buscar artista...",
                                        color = Gris
                                    )
                                },
                                modifier = Modifier
                                    .widthIn(max = 500.dp)
                                    .fillMaxWidth(),
                                singleLine = true,
                                shape = RoundedCornerShape(12.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = Tarjeta,
                                    unfocusedContainerColor = Tarjeta,
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    focusedBorderColor = Verde,
                                    unfocusedBorderColor = Color.DarkGray,
                                    cursorColor = Verde
                                )
                            )
                        }
                    }

                    // 3. ARTISTAS
                    item {

                        if (esPantallaGrande) {

                            // WEB: DOS COLUMNAS
                            Row(
                                modifier = Modifier
                                    .widthIn(max = 1100.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(24.dp),
                                verticalAlignment = Alignment.Top
                            ) {

                                SeccionDestacados(
                                    artistas = artistas,
                                    modifier = Modifier.weight(1f),
                                    onOpen = { navegador.openUri(it) }
                                )

                                SeccionLista(
                                    artistas = artistasFiltrados,
                                    modifier = Modifier.weight(1f),
                                    onOpen = { navegador.openUri(it) }
                                )
                            }

                        } else {

                            // ANDROID: UNA COLUMNA
                            Column(
                                modifier = Modifier
                                    .widthIn(max = 1100.dp)
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(24.dp)
                            ) {

                                SeccionDestacados(
                                    artistas = artistas,
                                    modifier = Modifier.fillMaxWidth(),
                                    onOpen = { navegador.openUri(it) }
                                )

                                SeccionLista(
                                    artistas = artistasFiltrados,
                                    modifier = Modifier.fillMaxWidth(),
                                    onOpen = { navegador.openUri(it) }
                                )
                            }
                        }
                    }

                    // 4. PIE DE PÁGINA
                    item {
                        Box(
                            modifier = Modifier
                                .widthIn(max = 1100.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Fotografías y datos: Spotify",
                                color = Gris,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

// ARTISTAS DESTACADOS
@Composable
private fun SeccionDestacados(
    artistas: List<Artista>,
    modifier: Modifier = Modifier,
    onOpen: (String) -> Unit
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Artistas destacados",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            artistas.take(2).forEach { artista ->

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            onOpen(artista.spotifyUrl)
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Tarjeta
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        FotoArtista(
                            artista = artista,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(12.dp))
                        )

                        Text(
                            text = artista.nombre,
                            color = Color.White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Artista",
                            color = Gris,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}

// LISTA DE ARTISTAS
@Composable
private fun SeccionLista(
    artistas: List<Artista>,
    modifier: Modifier = Modifier,
    onOpen: (String) -> Unit
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Todos los artistas",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        if (artistas.isEmpty()) {
            Text(
                text = "No se encontraron artistas",
                color = Gris
            )
        }

        artistas.forEach { artista ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Tarjeta)
                    .clickable {
                        onOpen(artista.spotifyUrl)
                    }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                FotoArtista(
                    artista = artista,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(10.dp))
                )

                Spacer(Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = artista.nombre,
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Ver en Spotify",
                        color = Gris,
                        fontSize = 14.sp
                    )
                }

                Text(
                    text = "›",
                    color = Verde,
                    fontSize = 30.sp
                )
            }
        }
    }
}

// COMPONENTE REUTILIZABLE PARA LAS FOTOS
@Composable
private fun FotoArtista(
    artista: Artista,
    modifier: Modifier = Modifier
) {

    var errorCarga by remember(artista.imagenUrl) {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier.background(Color.DarkGray),
        contentAlignment = Alignment.Center
    ) {

        if (artista.imagenUrl != null && !errorCarga) {

            AsyncImage(
                model = artista.imagenUrl,
                contentDescription = artista.nombre,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                onError = {
                    errorCarga = true
                }
            )

        } else {

            Text(
                text = artista.nombre.take(1),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
