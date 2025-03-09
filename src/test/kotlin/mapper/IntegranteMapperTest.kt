package mapper

import org.example.configuration.Configuration
import org.example.configuration.ConfigurationProperties
import org.example.dto.IntegranteDTO
import org.example.dto.IntegranteXmlDTO
import org.example.mapper.toDto
import org.example.mapper.toModel
import org.example.mapper.toXmlDTO
import org.example.models.Entrenador
import org.example.models.Especialidad
import org.example.models.Jugador
import org.example.models.Posicion
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.time.LocalDate
import java.time.LocalDateTime

class IntegranteMapperTest {

    @Test
    @DisplayName("Integrante to DTO")
    fun testToDTO() {
        val jugador = Jugador(1L, "Pepe", "Garcia", LocalDate.of(2003, 8, 12), LocalDate.now(), 2000.0, "Caca", LocalDateTime.now(), LocalDateTime.now(), false, Posicion.PORTERO, 18, 1.75, 80.0, 1, 1)
        val entrenador = Entrenador(1L, "Pepe", "Garcia", LocalDate.of(2003, 8, 12), LocalDate.now(), 2000.0, "Caca", LocalDateTime.now(), LocalDateTime.now(), false, Especialidad.ENTRENADOR_PRINCIPAL)

        val jugadorDTO = jugador.toDto()
        val entrenadorDTO = entrenador.toDto()

        // assertAll de Jugador
        assertAll(
            { assertEquals(jugador.id, jugadorDTO.id) },
            { assertEquals(jugador.nombre, jugadorDTO.nombre) },
            { assertEquals(jugador.apellidos, jugadorDTO.apellidos) },
            { assertEquals(jugador.fecha_nacimiento.toString(), jugadorDTO.fecha_nacimiento) },
            { assertEquals(jugador.fecha_incorporacion.toString(), jugadorDTO.fecha_incorporacion) },
            { assertEquals(jugador.salario, jugadorDTO.salario) },
            { assertEquals(jugador.pais, jugadorDTO.pais) },
            { assertEquals(jugador.posicion.toString(), jugadorDTO.posicion) },
            { assertEquals(jugador.dorsal, jugadorDTO.dorsal) },
            { assertEquals(jugador.altura, jugadorDTO.altura) },
            { assertEquals(jugador.peso, jugadorDTO.peso) },
            { assertEquals(jugador.goles, jugadorDTO.goles) },
            { assertEquals(jugador.partidos_jugados, jugadorDTO.partidos_jugados) }
        )
        // assertAll de Entrenador
        assertAll(
            { assertEquals(entrenador.id, entrenadorDTO.id) },
            { assertEquals(entrenador.nombre, entrenadorDTO.nombre) },
            { assertEquals(entrenador.apellidos, entrenadorDTO.apellidos) },
            { assertEquals(entrenador.fecha_nacimiento.toString(), entrenadorDTO.fecha_nacimiento) },
            { assertEquals(entrenador.fecha_incorporacion.toString(), entrenadorDTO.fecha_incorporacion) },
            { assertEquals(entrenador.salario, entrenadorDTO.salario) },
            { assertEquals(entrenador.pais, entrenadorDTO.pais) },
            { assertEquals(entrenador.especialidad.toString(), entrenadorDTO.especialidad) },
        )
    }

    @Test
    @DisplayName("DTO to Model")
    fun testToModel() {
        val jugadorDTO = IntegranteDTO(1L, "Pepe", "Garcia", "2003-08-12", "2025-08-12", 2000.0, "Caca", "Jugador", null, Posicion.PORTERO.toString(), 18, 1.75, 80.0, 1, 1)
        val entrenadorDTO = IntegranteDTO(1L, "Pepe", "Garcia", "2003-08-12", "2025-08-12", 2000.0, "Caca", "Entrenador", Especialidad.ENTRENADOR_PRINCIPAL.toString(), null, null, null, null, null, null)

        val jugador = jugadorDTO.toModel()
        val entrenador = entrenadorDTO.toModel()

        // assertAll de Jugador
        assertAll(
            { assertEquals(jugador.id, jugadorDTO.id) },
            { assertEquals(jugador.nombre, jugadorDTO.nombre) },
            { assertEquals(jugador.apellidos, jugadorDTO.apellidos) },
            { assertEquals(jugador.fecha_nacimiento.toString(), jugadorDTO.fecha_nacimiento) },
            { assertEquals(jugador.fecha_incorporacion.toString(), jugadorDTO.fecha_incorporacion) },
            { assertEquals(jugador.salario, jugadorDTO.salario) },
            { assertEquals(jugador.pais, jugadorDTO.pais) },
            { assertEquals("Jugador", jugadorDTO.rol) },
            { assertEquals((jugador as Jugador).posicion.toString(), jugadorDTO.posicion) },
            { assertEquals((jugador as Jugador).dorsal, jugadorDTO.dorsal) },
            { assertEquals((jugador as Jugador).altura, jugadorDTO.altura) },
            { assertEquals((jugador as Jugador).peso, jugadorDTO.peso) },
            { assertEquals((jugador as Jugador).goles, jugadorDTO.goles) },
            { assertEquals((jugador as Jugador).partidos_jugados, jugadorDTO.partidos_jugados) }
        )
        // assertAll de Entrenador
        assertAll(
            { assertEquals(entrenador.id, entrenadorDTO.id) },
            { assertEquals(entrenador.nombre, entrenadorDTO.nombre) },
            { assertEquals(entrenador.apellidos, entrenadorDTO.apellidos) },
            { assertEquals(entrenador.fecha_nacimiento.toString(), entrenadorDTO.fecha_nacimiento) },
            { assertEquals(entrenador.fecha_incorporacion.toString(), entrenadorDTO.fecha_incorporacion) },
            { assertEquals(entrenador.salario, entrenadorDTO.salario) },
            { assertEquals(entrenador.pais, entrenadorDTO.pais) },
            { assertEquals((entrenador as Entrenador).especialidad.toString(), entrenadorDTO.especialidad) },
        )
    }
    @Test
    @DisplayName("IntegranteXML to DTO")
    fun testXmlToDTO() {
        val jugador = Jugador(1L, "Pepe", "Garcia", LocalDate.of(2003, 8, 12), LocalDate.now(), 2000.0, "Caca", LocalDateTime.now(), LocalDateTime.now(), false, Posicion.PORTERO, 18, 1.75, 80.0, 1, 1)
        val entrenador = Entrenador(1L, "Pepe", "Garcia", LocalDate.of(2003, 8, 12), LocalDate.now(), 2000.0, "Caca", LocalDateTime.now(), LocalDateTime.now(), false, Especialidad.ENTRENADOR_PRINCIPAL)

        val jugadorDTO = jugador.toXmlDTO()
        val entrenadorDTO = entrenador.toXmlDTO()

        // assertAll de Jugador
        assertAll(
            { assertEquals(jugador.id, jugadorDTO.id) },
            { assertEquals(jugador.nombre, jugadorDTO.nombre) },
            { assertEquals(jugador.apellidos, jugadorDTO.apellidos) },
            { assertEquals(jugador.fecha_nacimiento.toString(), jugadorDTO.fecha_nacimiento) },
            { assertEquals(jugador.fecha_incorporacion.toString(), jugadorDTO.fecha_incorporacion) },
            { assertEquals(jugador.salario, jugadorDTO.salario) },
            { assertEquals(jugador.pais, jugadorDTO.pais) },
            { assertEquals(jugador.posicion.toString(), jugadorDTO.posicion) },
            { assertEquals(jugador.dorsal.toString(), jugadorDTO.dorsal) },
            { assertEquals(jugador.altura.toString(), jugadorDTO.altura) },
            { assertEquals(jugador.peso.toString(), jugadorDTO.peso) },
            { assertEquals(jugador.goles.toString(), jugadorDTO.goles) },
            { assertEquals(jugador.partidos_jugados.toString(), jugadorDTO.partidos_jugados) }
        )
        // assertAll de Entrenador
        assertAll(
            { assertEquals(entrenador.id, entrenadorDTO.id) },
            { assertEquals(entrenador.nombre, entrenadorDTO.nombre) },
            { assertEquals(entrenador.apellidos, entrenadorDTO.apellidos) },
            { assertEquals(entrenador.fecha_nacimiento.toString(), entrenadorDTO.fecha_nacimiento) },
            { assertEquals(entrenador.fecha_incorporacion.toString(), entrenadorDTO.fecha_incorporacion) },
            { assertEquals(entrenador.salario, entrenadorDTO.salario) },
            { assertEquals(entrenador.pais, entrenadorDTO.pais) },
            { assertEquals(entrenador.especialidad.toString(), entrenadorDTO.especialidad) },
        )
    }
    @Test
    @DisplayName("DTO to IntegranteXML")
    fun testDtoToXML() {
        val jugadorDTO = IntegranteXmlDTO(1L, "Jugador", "Pepe", "Garcia", "2003-08-12", "2025-08-12", 2000.0, "Caca", null, Posicion.PORTERO.toString(), "18", "1.75", "80.0", "1", "1")
        val entrenadorDTO = IntegranteXmlDTO(1L, "Entrenador", "Pepe", "Garcia", "2003-08-12", "2025-08-12", 2000.0, "Caca", Especialidad.ENTRENADOR_PRINCIPAL.toString(), null, null, null, null, null, null)

        val jugador = jugadorDTO.toModel()
        val entrenador = entrenadorDTO.toModel()

        // assertAll de Jugador
        assertAll(
            { assertEquals(jugador.id, jugadorDTO.id) },
            { assertEquals(jugador.nombre, jugadorDTO.nombre) },
            { assertEquals(jugador.apellidos, jugadorDTO.apellidos) },
            { assertEquals(jugador.fecha_nacimiento.toString(), jugadorDTO.fecha_nacimiento) },
            { assertEquals(jugador.fecha_incorporacion.toString(), jugadorDTO.fecha_incorporacion) },
            { assertEquals(jugador.salario, jugadorDTO.salario) },
            { assertEquals(jugador.pais, jugadorDTO.pais) },
            { assertEquals("Jugador", jugadorDTO.rol) },
            { assertEquals((jugador as Jugador).posicion.toString(), jugadorDTO.posicion) },
            { assertEquals((jugador as Jugador).dorsal.toString(), jugadorDTO.dorsal) },
            { assertEquals((jugador as Jugador).altura.toString(), jugadorDTO.altura) },
            { assertEquals((jugador as Jugador).peso.toString(), jugadorDTO.peso) },
            { assertEquals((jugador as Jugador).goles.toString(), jugadorDTO.goles) },
            { assertEquals((jugador as Jugador).partidos_jugados.toString(), jugadorDTO.partidos_jugados) }
        )
        // assertAll de Entrenador
        assertAll(
            { assertEquals(entrenador.id, entrenadorDTO.id) },
            { assertEquals(entrenador.nombre, entrenadorDTO.nombre) },
            { assertEquals(entrenador.apellidos, entrenadorDTO.apellidos) },
            { assertEquals(entrenador.fecha_nacimiento.toString(), entrenadorDTO.fecha_nacimiento) },
            { assertEquals(entrenador.fecha_incorporacion.toString(), entrenadorDTO.fecha_incorporacion) },
            { assertEquals(entrenador.salario, entrenadorDTO.salario) },
            { assertEquals(entrenador.pais, entrenadorDTO.pais) },
            { assertEquals((entrenador as Entrenador).especialidad.toString(), entrenadorDTO.especialidad) },
        )
    }
}