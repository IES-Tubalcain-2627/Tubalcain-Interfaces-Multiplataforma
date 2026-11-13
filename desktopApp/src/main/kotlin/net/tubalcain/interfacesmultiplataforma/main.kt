package net.tubalcain.interfacesmultiplataforma

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import net.tubalcain.interfacesmultiplataforma.planazoApp.PlanazoApp

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "InterfacesMultiplataforma",
    ) {
        PlanazoApp()
    }
}