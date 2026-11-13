package net.tubalcain.interfacesmultiplataforma.ejemplos


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EstudianteAppProfesor() {

    var mensaje by remember {
        mutableStateOf("Selecciona una opción")
    }

    MaterialTheme {

        // 1. BOX RAÍZ
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow)
        ) {

            // 2. COLUMN PRINCIPAL
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .safeDrawingPadding()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                // 3. BARRA SUPERIOR
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "DAM2",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        text = "⋮",
                        fontSize = 28.sp,
                        color = Color.Black
                    )
                }

                // 4. TARJETA DE BIENVENIDA
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Mi espacio DAM2",
                            fontSize = 27.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Text(
                            text = "Panel del estudiante",
                            fontSize = 17.sp,
                            color = Color.Gray
                        )
                    }
                }

                // 5. FILA DE TARJETAS
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    // ASIGNATURAS
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp)
                        ) {
                            Text(
                                text = "4",
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue
                            )

                            Text(
                                text = "Asignaturas",
                                color = Color.Gray
                            )
                        }
                    }

                    // TAREAS
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp)
                        ) {
                            Text(
                                text = "3",
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF16A34A)
                            )

                            Text(
                                text = "Tareas",
                                color = Color.Gray
                            )
                        }
                    }
                }

                // 6. TÍTULO DE NOVEDADES
                Text(
                    text = "Últimas novedades",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                // 7. TARJETA DE NOVEDADES
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(52.dp)
                                .background(
                                    Color(0xFFE3EDFF),
                                    RoundedCornerShape(12.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "▤",
                                fontSize = 28.sp,
                                color = Color.Blue
                            )
                        }

                        Spacer(Modifier.width(12.dp))

                        Column {
                            Text(
                                text = "Desarrollo de Interfaces",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.Black
                            )

                            Text(
                                text = "Nueva práctica de Compose",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }

                // 8. BOTÓN
                Button(
                    onClick = {
                        mensaje = "Entrando al aula virtual..."
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1769EE)
                    )
                ) {
                    Text(
                        text = "Entrar al aula virtual",
                        fontSize = 16.sp
                    )
                }

                // 9. MENSAJE INFERIOR
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Text(
                        text = mensaje,
                        modifier = Modifier.padding(20.dp),
                        color = Color.Gray
                    )
                }
            }
        }
    }
}
