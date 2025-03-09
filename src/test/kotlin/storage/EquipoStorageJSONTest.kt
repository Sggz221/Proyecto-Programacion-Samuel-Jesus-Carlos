package storage

import org.example.exceptions.Exceptions
import org.example.models.Entrenador
import org.example.models.Especialidad
import org.example.models.Jugador
import org.example.models.Posicion
import org.example.storage.EquipoStorageJSON
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.time.LocalDate

class EquipoStorageJSONTest {

    val storage = EquipoStorageJSON()

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
        val file = File(tempDir,"data.csv")
        file.writeText(
            "[\n" +
                    "  {\n" +
                    "    \"id\": 21,\n" +
                    "    \"nombre\": \"Carlos\",\n" +
                    "    \"apellidos\": \"Santana\",\n" +
                    "    \"fecha_nacimiento\": \"1985-01-15\",\n" +
                    "    \"fecha_incorporacion\": \"2002-03-01\",\n" +
                    "    \"salario\": 33000.0,\n" +
                    "    \"pais\": \"Brasil\",\n" +
                    "    \"rol\": \"Jugador\",\n" +
                    "    \"especialidad\": \"\",\n" +
                    "    \"posicion\": \"DELANTERO\",\n" +
                    "    \"dorsal\": 9,\n" +
                    "    \"altura\": 1.82,\n" +
                    "    \"peso\": 74.0,\n" +
                    "    \"goles\": 50,\n" +
                    "    \"partidos_jugados\": 140\n" +
                    "  }" +
                    "]\n"
        )

        val equipo = storage.fileRead(file)


        assertEquals(1, equipo.size)

        val jugadorExpected = Jugador(
            id = 21,
            nombre ="Carlos",
            apellidos = "Santana",
            fecha_nacimiento = LocalDate.parse("1985-01-15"),
            fecha_incorporacion = LocalDate.parse("2002-03-01"),
            salario = 33000.0,
            pais = "Brasil",
            posicion = Posicion.DELANTERO,
            dorsal = 9,
            altura = 1.82,
            peso = 74.0,
            goles = 50,
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
        val file = File(tempDir,"data.json")

        val jugador = Jugador(
            id = 21,
            nombre ="Carlos",
            apellidos = "Santana",
            fecha_nacimiento = LocalDate.parse("1985-01-15"),
            fecha_incorporacion = LocalDate.parse("2002-03-01"),
            salario = 33000.0,
            pais = "Brasil",
            posicion = Posicion.DELANTERO,
            dorsal = 9,
            altura = 1.82,
            peso = 74.0,
            goles = 50,
            partidos_jugados = 140
        )

        val entrenador = Entrenador(
            id = 21,
            nombre ="Carlos",
            apellidos = "Santana",
            fecha_nacimiento = LocalDate.parse("1985-01-15"),
            fecha_incorporacion = LocalDate.parse("2002-03-01"),
            salario = 33000.0,
            pais = "Brasil",
            especialidad = Especialidad.ENTRENADOR_PRINCIPAL
        )

        val equipo = listOf(jugador,entrenador)

        storage.fileWrite(equipo, file)

        val expectedString = "[\n" +
                "    {\n" +
                "        \"id\": 21,\n" +
                "        \"nombre\": \"Carlos\",\n" +
                "        \"apellidos\": \"Santana\",\n" +
                "        \"fecha_nacimiento\": \"1985-01-15\",\n" +
                "        \"fecha_incorporacion\": \"2002-03-01\",\n" +
                "        \"salario\": 33000.0,\n" +
                "        \"pais\": \"Brasil\",\n" +
                "        \"rol\": \"Jugador\",\n" +
                "        \"especialidad\": \"\",\n" +
                "        \"posicion\": \"DELANTERO\",\n" +
                "        \"dorsal\": 9,\n" +
                "        \"altura\": 1.82,\n" +
                "        \"peso\": 74.0,\n" +
                "        \"goles\": 50,\n" +
                "        \"partidos_jugados\": 140\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 21,\n" +
                "        \"nombre\": \"Carlos\",\n" +
                "        \"apellidos\": \"Santana\",\n" +
                "        \"fecha_nacimiento\": \"1985-01-15\",\n" +
                "        \"fecha_incorporacion\": \"2002-03-01\",\n" +
                "        \"salario\": 33000.0,\n" +
                "        \"pais\": \"Brasil\",\n" +
                "        \"rol\": \"Entrenador\",\n" +
                "        \"especialidad\": \"ENTRENADOR_PRINCIPAL\",\n" +
                "        \"posicion\": \"\",\n" +
                "        \"dorsal\": null,\n" +
                "        \"altura\": null,\n" +
                "        \"peso\": null,\n" +
                "        \"goles\": null,\n" +
                "        \"partidos_jugados\": null\n" +
                "    }\n" +
                "]"

        val actualString = file.readText()

        assertEquals(expectedString, actualString)

    }
}