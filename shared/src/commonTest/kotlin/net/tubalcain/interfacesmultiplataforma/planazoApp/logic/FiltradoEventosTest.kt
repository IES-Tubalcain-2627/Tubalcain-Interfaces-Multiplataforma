
package net.tubalcain.interfacesmultiplataforma.planazoApp.logic

import net.tubalcain.interfacesmultiplataforma.planazoApp.model.Evento
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FiltradoEventosTest {

    private val eventos = listOf(
        Evento(
            "Festival Urbano", "Música",
            "Sábado", "🎤", "Festival musical"
        ),
        Evento(
            "Torneo EA FC", "Gaming",
            "Viernes", "🎮", "Torneo de videojuegos"
        ),
        Evento(
            "Batalla de DJs", "Música",
            "Sábado", "🎧", "Concurso de DJs"
        )
    )

    @Test
    fun buscarNoDistingueMayusculas() {
        val resultado = filtrarEventos(
            eventos, "festival", "Todos"
        )

        assertEquals(1, resultado.size)
        assertEquals("Festival Urbano", resultado[0].nombre)
    }

    @Test
    fun filtrarPorCategoria() {
        val resultado = filtrarEventos(
            eventos, "", "Música"
        )

        assertEquals(2, resultado.size)
    }

    @Test
    fun combinarFiltrosSinCoincidencias() {
        val resultado = filtrarEventos(
            eventos, "Festival", "Gaming"
        )

        assertTrue(resultado.isEmpty())
    }

    @Test
    fun sinFiltrosDevuelveTodos() {
        val resultado = filtrarEventos(
            eventos, "", "Todos"
        )

        assertEquals(3, resultado.size)
    }
}
