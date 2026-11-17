
package net.tubalcain.interfacesmultiplataforma.planazoApp.logic

import net.tubalcain.interfacesmultiplataforma.planazoApp.model.Evento

/**
 * Filtra una lista de eventos por nombre y categoría.
 *
 * @param eventos Lista original de eventos.
 * @param busqueda Texto introducido por el usuario.
 * @param categoriaElegida Categoría seleccionada.
 * @return Eventos que cumplen ambos filtros.
 */
fun filtrarEventos(
    eventos: List<Evento>,
    busqueda: String,
    categoriaElegida: String
): List<Evento> {
    return eventos.filter { evento ->
        evento.nombre.contains(
            busqueda,
            ignoreCase = true
        ) && (
                categoriaElegida == "Todos" ||
                        evento.categoria == categoriaElegida
                )
    }
}
