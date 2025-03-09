package storage

import org.example.exceptions.Exceptions
import org.example.models.Entrenador
import org.example.models.Especialidad
import org.example.models.Jugador
import org.example.models.Posicion
import org.example.storage.EquipoStorageBIN
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.time.LocalDate

class EquipoStorageBINTest {

    val storage = EquipoStorageBIN()

    @Test
    fun fileReadThrowsFileNotExistsException(){
        val file = File("noExiste.txt")

        val expected = "Error en el storage: El fichero no existe, la ruta especificada no es un fichero o no se tienen permisos de lectura"
        val actual = org.junit.jupiter.api.assertThrows<Exceptions.StorageException> { storage.fileRead(file) }

        assertEquals(expected, actual.message)
    }

    @Test
    fun fileWriteThrowsFileNotExistsException(@TempDir tempDir: File) {
        val file = File(tempDir,"fhgdgdh/noExiste.txt")

        val entrenador = Entrenador(
            id = 5,
            nombre ="Tom",
            apellidos = "Baker",
            fecha_nacimiento = LocalDate.parse("1984-03-20"),
            fecha_incorporacion = LocalDate.parse("2001-05-15"),
            salario = 32000.0,
            pais = "Inglaterra",
            especialidad = Especialidad.ENTRENADOR_PRINCIPAL
        )
        val equipo = listOf(entrenador)


        val expected = "Error en el storage: El directorio padre del fichero no existe"
        val actual = org.junit.jupiter.api.assertThrows<Exceptions.StorageException> { storage.fileWrite(equipo, file) }

        assertEquals(expected, actual.message)
    }

    @Test
    fun importAndExportOK(@TempDir tempDir: File) {
        val file = File(tempDir, "data.bin")

        val entrenador = Entrenador(id = 5,
            nombre ="Tom",
            apellidos = "Baker",
            fecha_nacimiento = LocalDate.parse("1984-03-20"),
            fecha_incorporacion = LocalDate.parse("2001-05-15"),
            salario = 32000.0,
            pais = "Inglaterra",
            especialidad = Especialidad.ENTRENADOR_PRINCIPAL)

        val jugador = Jugador(id = 5,
            nombre ="Tom",
            apellidos = "Baker",
            fecha_nacimiento = LocalDate.parse("1984-03-20"),
            fecha_incorporacion = LocalDate.parse("2001-05-15"),
            salario = 32000.0,
            pais = "Inglaterra",
            posicion = Posicion.CENTROCAMPISTA,
            dorsal = 8,
            altura = 1.72,
            peso = 63.0,
            goles = 30,
            partidos_jugados = 140)

        val equipo = listOf(entrenador, jugador)

        storage.fileWrite(equipo, file)

        val equipoLeido = storage.fileRead(file)

        // Igualamos el valor de estos campos porque van a ser distintos ya que el valor por defecto es LocalDateTime.now()
        equipo[0].createdAt = equipoLeido[0].createdAt
        equipo[1].createdAt = equipoLeido[1].createdAt
        equipo[0].updatedAt = equipoLeido[0].updatedAt
        equipo[1].updatedAt = equipoLeido[1].updatedAt

        val entrenadorLeido = equipoLeido[0]
        val jugadorLeido = equipoLeido[1]


        // assertAll de Jugador
        org.junit.jupiter.api.assertAll(
            { assertEquals(jugador.id, jugadorLeido.id) },
            { assertEquals(jugador.nombre, jugadorLeido.nombre) },
            { assertEquals(jugador.apellidos, jugadorLeido.apellidos) },
            { assertEquals(jugador.fecha_nacimiento, jugadorLeido.fecha_nacimiento) },
            { assertEquals(jugador.fecha_incorporacion, jugadorLeido.fecha_incorporacion) },
            { assertEquals(jugador.salario, jugadorLeido.salario) },
            { assertEquals(jugador.pais, jugadorLeido.pais) },
            { assertEquals(jugador.posicion, (jugadorLeido as Jugador).posicion) },
            { assertEquals(jugador.dorsal, (jugadorLeido as Jugador).dorsal) },
            { assertEquals(jugador.altura, (jugadorLeido as Jugador).altura) },
            { assertEquals(jugador.peso, (jugadorLeido as Jugador).peso) },
            { assertEquals(jugador.goles, (jugadorLeido as Jugador).goles) },
            { assertEquals(jugador.partidos_jugados, (jugadorLeido as Jugador).partidos_jugados) }
        )
        // assertAll de Entrenador
        org.junit.jupiter.api.assertAll(
            { assertEquals(entrenador.id, entrenadorLeido.id) },
            { assertEquals(entrenador.nombre, entrenadorLeido.nombre) },
            { assertEquals(entrenador.apellidos, entrenadorLeido.apellidos) },
            { assertEquals(entrenador.fecha_nacimiento, entrenadorLeido.fecha_nacimiento) },
            { assertEquals(entrenador.fecha_incorporacion, entrenadorLeido.fecha_incorporacion) },
            { assertEquals(entrenador.salario, entrenadorLeido.salario) },
            { assertEquals(entrenador.pais, entrenadorLeido.pais) },
            { assertEquals(entrenador.especialidad, (entrenadorLeido as Entrenador).especialidad) },
        )

    }
}