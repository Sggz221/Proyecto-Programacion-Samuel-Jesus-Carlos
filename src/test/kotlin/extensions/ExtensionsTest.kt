package extensions

import org.example.extensions.copy
import org.example.models.Entrenador
import org.example.models.Especialidad
import org.example.models.Jugador
import org.example.models.Posicion
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.time.LocalDate
import java.time.LocalDateTime

class ExtensionsTest {
    @Test
    @DisplayName("Copy de Jugador y Entrenador")
    fun copyTest(){
        val jugador = Jugador(1L, "Pepe", "Garcia", LocalDate.of(2003, 8, 12), LocalDate.now(), 2000.0, "Caca", LocalDateTime.now(), LocalDateTime.now(), false, Posicion.PORTERO, 18, 1.75, 80.0, 1, 1)
        val entrenador = Entrenador(1L, "Pepe", "Garcia", LocalDate.of(2003, 8, 12), LocalDate.now(), 2000.0, "Caca", LocalDateTime.now(), LocalDateTime.now(), false, Especialidad.ENTRENADOR_PRINCIPAL)

        val newJugador = jugador.copy()
        val newEntrenador = entrenador.copy()

        // assertAll de Jugador
        assertAll(
            { assertEquals(jugador.id, newJugador.id) },
            { assertEquals(jugador.nombre, newJugador.nombre) },
            { assertEquals(jugador.apellidos, newJugador.apellidos) },
            { assertEquals(jugador.fecha_nacimiento, newJugador.fecha_nacimiento) },
            { assertEquals(jugador.fecha_incorporacion, newJugador.fecha_incorporacion) },
            { assertEquals(jugador.salario, newJugador.salario) },
            { assertEquals(jugador.pais, newJugador.pais) },
            { assertEquals(jugador.posicion, newJugador.posicion) },
            { assertEquals(jugador.dorsal, newJugador.dorsal) },
            { assertEquals(jugador.altura, newJugador.altura) },
            { assertEquals(jugador.peso, newJugador.peso) },
            { assertEquals(jugador.goles, newJugador.goles) },
            { assertEquals(jugador.partidos_jugados, newJugador.partidos_jugados) }
        )
        // assertAll de Entrenador
        assertAll(
            { assertEquals(entrenador.id, newEntrenador.id) },
            { assertEquals(entrenador.nombre, newEntrenador.nombre) },
            { assertEquals(entrenador.apellidos, newEntrenador.apellidos) },
            { assertEquals(entrenador.fecha_nacimiento, newEntrenador.fecha_nacimiento) },
            { assertEquals(entrenador.fecha_incorporacion, newEntrenador.fecha_incorporacion) },
            { assertEquals(entrenador.salario, newEntrenador.salario) },
            { assertEquals(entrenador.pais, newEntrenador.pais) },
            { assertEquals(entrenador.especialidad, newEntrenador.especialidad) },
        )
    }
}