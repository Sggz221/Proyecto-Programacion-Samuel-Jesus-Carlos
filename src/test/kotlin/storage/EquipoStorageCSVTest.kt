package storage

import org.example.exceptions.Exceptions
import org.example.models.*
import org.example.storage.EquipoStorageCSV
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.time.LocalDate

class EquipoStorageCSVTest {

    val storage = EquipoStorageCSV()

    @Test
    fun fileReadThrowsFileNotExistsException(){
        val file = File("noExiste.txt")

        val expected = "Error en el storage: El fichero no existe, la ruta especificada no es un fichero o no se tienen permisos de lectura"
        val actual = assertThrows<Exceptions.StorageException> { storage.fileRead(file) }

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
        val actual = assertThrows<Exceptions.StorageException> { storage.fileWrite(equipo, file) }

        assertEquals(expected, actual.message)
    }


    @Test
    fun importOk(@TempDir tempDir: File){
        val file = File(tempDir,"data.csv")
        file.writeText(
            "id,nombre,apellidos,fecha_nacimiento,fecha_incorporacion,salario,pais,rol,especialidad,posicion,dorsal,altura,peso,goles,partidos_jugados\n" +
                    "5,Tom,Baker,1984-03-20,2001-05-15,32000.0,Inglaterra,Jugador,,CENTROCAMPISTA,8,1.72,63.0,30,140\n"
        )

        val equipo = storage.fileRead(file)


        assertEquals(1, equipo.size)

        val jugadorExpected = Jugador(
            id = 5,
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
            partidos_jugados = 140
        )

        val jugadorActual = equipo.first()

        org.junit.jupiter.api.assertAll(
            { assertEquals(jugadorExpected.id, jugadorActual.id) },
            { assertEquals(jugadorExpected.nombre, jugadorActual.nombre) },
            { assertEquals(jugadorExpected.apellidos, jugadorActual.apellidos) },
            { assertEquals(jugadorExpected.fecha_nacimiento, jugadorActual.fecha_nacimiento) },
            { assertEquals(jugadorExpected.fecha_incorporacion, jugadorActual.fecha_incorporacion) },
            { assertEquals(jugadorExpected.salario, jugadorActual.salario) },
            { assertEquals(jugadorExpected.pais, jugadorActual.pais) },
            { assertEquals(jugadorExpected.posicion, (jugadorActual as Jugador).posicion) },
            { assertEquals(jugadorExpected.dorsal, (jugadorActual as Jugador).dorsal) },
            { assertEquals(jugadorExpected.altura, (jugadorActual as Jugador).altura) },
            { assertEquals(jugadorExpected.peso, (jugadorActual as Jugador).peso) },
            { assertEquals(jugadorExpected.goles, (jugadorActual as Jugador).goles) },
            { assertEquals(jugadorExpected.partidos_jugados, (jugadorActual as Jugador).partidos_jugados) }
        )
    }

    @Test
    fun exportOk (@TempDir tempDir: File){
        val file = File(tempDir,"data.csv")

        val jugador = Jugador(
            id = 5,
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
            partidos_jugados = 140
        )

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

        val equipo = listOf(jugador,entrenador)

        storage.fileWrite(equipo, file)

        val expectedString = "id,nombre,apellidos,fecha_nacimiento,fecha_incorporacion,salario,pais,rol,especialidad,posicion,dorsal,altura,peso,goles,partidos_jugados\n" +
                "5,Tom,Baker,1984-03-20,2001-05-15,32000.0,Inglaterra,Jugador,,CENTROCAMPISTA,8,1.72,63.0,30,140\n" +
                "5,Tom,Baker,1984-03-20,2001-05-15,32000.0,Inglaterra,Entrenador,ENTRENADOR_PRINCIPAL,,,,,,\n"

        val actualString = file.readText()

        assertEquals(expectedString, actualString)

    }
}