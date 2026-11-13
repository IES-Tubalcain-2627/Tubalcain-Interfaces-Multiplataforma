package net.tubalcain.interfacesmultiplataforma.ejemplos


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun RegisterScreen() {

    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmarPassword by remember { mutableStateOf("") }

    var mostrarPassword by remember { mutableStateOf(false) }
    var mensaje by remember { mutableStateOf("") }
    var registroCorrecto by remember { mutableStateOf(false) }

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier
                    .widthIn(max = 420.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text(
                    text = "Crear cuenta",
                    style = MaterialTheme.typography.headlineMedium
                )

                Text("Regístrate para continuar")

                // NOMBRE
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre completo") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                // EMAIL
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                // CONTRASEÑA
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = if (mostrarPassword) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    trailingIcon = {
                        TextButton(
                            onClick = {
                                mostrarPassword = !mostrarPassword
                            }
                        ) {
                            Text(if (mostrarPassword) "Ocultar" else "Ver")
                        }
                    }
                )

                // CONFIRMAR CONTRASEÑA
                OutlinedTextField(
                    value = confirmarPassword,
                    onValueChange = { confirmarPassword = it },
                    label = { Text("Confirmar contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation()
                )

                // BOTÓN DE REGISTRO
                Button(
                    onClick = {

                        registroCorrecto = false

                        when {
                            nombre.isBlank() ||
                                    email.isBlank() ||
                                    password.isBlank() ||
                                    confirmarPassword.isBlank() -> {
                                mensaje = "Completa todos los campos"
                            }

                            !email.contains("@") -> {
                                mensaje = "Correo electrónico incorrecto"
                            }

                            password.length < 6 -> {
                                mensaje = "Mínimo 6 caracteres"
                            }

                            password != confirmarPassword -> {
                                mensaje = "Las contraseñas no coinciden"
                            }

                            else -> {
                                registroCorrecto = true
                                mensaje = "Registro completado correctamente"
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Crear cuenta")
                }

                // MENSAJE DEL REGISTRO
                if (mensaje.isNotEmpty()) {
                    Text(
                        text = mensaje,
                        color = if (registroCorrecto) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.error
                        }
                    )
                }
            }
        }
    }
}
