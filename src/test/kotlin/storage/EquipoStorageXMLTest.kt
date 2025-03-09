package storage

import org.example.exceptions.Exceptions
import org.example.models.Entrenador
import org.example.models.Especialidad
import org.example.models.Jugador
import org.example.models.Posicion
import org.example.storage.EquipoStorageXML
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.time.LocalDate

class EquipoStorageXMLTest {

    val storage = EquipoStorageXML()

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
    fun importOk(@TempDir tempDir: File){
        val file = File(tempDir,"data.xml")
        file.writeText(
            "<equipo>\n" +
                    "    <personal id=\"32\">\n" +
                    "        <tipo>Jugador</tipo>\n" +
                    "        <nombre>Sofía</nombre>\n" +
                    "        <apellidos>Gómez</apellidos>\n" +
                    "        <fechaNacimiento>2000-07-15</fechaNacimiento>\n" +
                    "        <fechaIncorporacion>2024-01-20</fechaIncorporacion>\n" +
                    "        <salario>39000.0</salario>\n" +
                    "        <pais>España</pais>\n" +
                    "        <especialidad/>\n" +
                    "        <posicion>DELANTERO</posicion>\n" +
                    "        <dorsal>11</dorsal>\n" +
                    "        <altura>1.65</altura>\n" +
                    "        <peso>60.25</peso>\n" +
                    "        <goles>15</goles>\n" +
                    "        <partidosJugados>30</partidosJugados>\n" +
                    "    </personal>\n" +
                    "</equipo>"
        )

        val equipo = storage.fileRead(file)


        assertEquals(1, equipo.size)

        val jugadorExpected = Jugador(
            id = 32,
            nombre ="Sofía",
            apellidos = "Gómez",
            fecha_nacimiento = LocalDate.parse("2000-07-15"),
            fecha_incorporacion = LocalDate.parse("2024-01-20"),
            salario = 39000.0,
            pais = "España",
            posicion = Posicion.DELANTERO,
            dorsal = 11,
            altura = 1.65,
            peso = 60.25,
            goles = 15,
            partidos_jugados = 30
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
            id = 32,
            nombre ="Sofía",
            apellidos = "Gómez",
            fecha_nacimiento = LocalDate.parse("2007-07-15"),
            fecha_incorporacion = LocalDate.parse("2024-01-20"),
            salario = 39000.0,
            pais = "España",
            posicion = Posicion.DELANTERO,
            dorsal = 11,
            altura = 1.65,
            peso = 60.25,
            goles = 15,
            partidos_jugados = 30
        )

        val entrenador = Entrenador(
            id = 33,
            nombre ="Roberto",
            apellidos = "Sánchez",
            fecha_nacimiento = LocalDate.parse("1975-02-10"),
            fecha_incorporacion = LocalDate.parse("2022-05-15"),
            salario = 60000.0,
            pais = "Uruguay",
            especialidad = Especialidad.ENTRENADOR_PORTEROS
        )

        val equipo = listOf(jugador,entrenador)

        storage.fileWrite(equipo, file)

        val expectedString = "<equipo>\n" +
                "    <personal id=\"32\">\n" +
                "        <tipo>Jugador</tipo>\n" +
                "        <nombre>Sofía</nombre>\n" +
                "        <apellidos>Gómez</apellidos>\n" +
                "        <fechaNacimiento>2007-07-15</fechaNacimiento>\n" +
                "        <fechaIncorporacion>2024-01-20</fechaIncorporacion>\n" +
                "        <salario>39000.0</salario>\n" +
                "        <pais>España</pais>\n" +
                "        <especialidad></especialidad>\n" +
                "        <posicion>DELANTERO</posicion>\n" +
                "        <dorsal>11</dorsal>\n" +
                "        <altura>1.65</altura>\n" +
                "        <peso>60.25</peso>\n" +
                "        <goles>15</goles>\n" +
                "        <partidosJugados>30</partidosJugados>\n" +
                "    </personal>\n" +
                "    <personal id=\"33\">\n" +
                "        <tipo>Entrenador</tipo>\n" +
                "        <nombre>Roberto</nombre>\n" +
                "        <apellidos>Sánchez</apellidos>\n" +
                "        <fechaNacimiento>1975-02-10</fechaNacimiento>\n" +
                "        <fechaIncorporacion>2022-05-15</fechaIncorporacion>\n" +
                "        <salario>60000.0</salario>\n" +
                "        <pais>Uruguay</pais>\n" +
                "        <especialidad>ENTRENADOR_PORTEROS</especialidad>\n" +
                "        <posicion></posicion>\n" +
                "        <dorsal></dorsal>\n" +
                "        <altura></altura>\n" +
                "        <peso></peso>\n" +
                "        <goles></goles>\n" +
                "        <partidosJugados></partidosJugados>\n" +
                "    </personal>\n" +
                "</equipo>"

        val actualString = file.readText()

        assertEquals(expectedString, actualString)

    }
}