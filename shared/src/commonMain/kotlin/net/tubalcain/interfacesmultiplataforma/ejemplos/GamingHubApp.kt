package net.tubalcain.interfacesmultiplataforma.ejemplos


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Videojuego(
    val nombre: String,
    val genero: String,
    val emoji: String
)

@Composable
fun GamingHubApp() {

    val fondo = Color(0xFF101820)
    val tarjeta = Color(0xFF1E2B35)
    val verde = Color(0xFF64F4B1)

    val videojuegos = listOf(
        Videojuego("Minecraft", "Sandbox", "🏗️"),
        Videojuego("Fortnite", "Battle Royale", "🏆"),
        Videojuego("Rocket League", "Deportes", "🚗"),
        Videojuego("Valorant", "Shooter", "🎯"),
        Videojuego("EA Sports FC", "Fútbol", "⚽"),
        Videojuego("GTA V", "Mundo abierto", "🚘"),
        Videojuego("Clash Royale", "Estrategia", "👑"),
        Videojuego("League of Legends", "MOBA", "⚔️")
    )

    MaterialTheme {

        Scaffold(
            containerColor = fondo,
            topBar = {
                Box(
                    modifier = Modifier.fillMaxWidth().background(fondo),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(fondo)
                            .padding(20.dp),

                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "🎮 GAMING HUB",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "DAM2",
                            color = verde,
                            fontSize = 16.sp
                        )
                    }
                }
            }

        ) { padding ->

            // Detectamos el ancho disponible
            BoxWithConstraints(
                modifier = Modifier.fillMaxSize().padding(padding)
            ) {
                val esPantallaGrande = maxWidth >= 800.dp


                // Scroll en toda la pantalla
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(
                        horizontal = if (esPantallaGrande) 32.dp else 16.dp,
                        vertical = 20.dp
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    // Cabecera
                    item {
                        Column(
                            modifier = Modifier.widthIn(max = 900.dp).fillMaxWidth()
                        ) {
                            Text(
                                text = "Explora videojuegos",
                                color = Color.White,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Descubre los títulos más populares",
                                color = Color.LightGray,
                                fontSize = 15.sp
                            )
                        }
                    }

                    items(videojuegos) { videojuego ->

                        Card(
                            modifier = Modifier.widthIn(max = 900.dp).fillMaxWidth(),
                            onClick = {
                                println("Navegando a: ${videojuego.nombre}")
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = tarjeta
                            )
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),

                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {

                                Text(
                                    text = videojuego.emoji,
                                    fontSize = 36.sp
                                )

                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = videojuego.nombre,
                                        color = Color.White,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Text(
                                        text = videojuego.genero,
                                        color = Color.LightGray,
                                        fontSize = 14.sp
                                    )
                                }

                                Text(
                                    text = "›",
                                    color = verde,
                                    fontSize = 30.sp
                                )
                            }
                        }
                    }

                    // Pie de pagina
                    item {
                        Text(
                            text = "Gaming Hub - DAM2",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}
