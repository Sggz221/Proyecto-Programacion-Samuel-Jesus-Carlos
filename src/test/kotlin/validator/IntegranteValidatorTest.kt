package validator

import org.example.exceptions.Exceptions
import org.example.models.Entrenador
import org.example.models.Especialidad
import org.example.models.Jugador
import org.example.models.Posicion
import org.example.validator.IntegranteValidator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.LocalDate
import java.time.LocalDateTime
import org.junit.jupiter.api.assertThrows

class IntegranteValidatorTest {

    val validador = IntegranteValidator()

    @Test
    fun validateThrowsExceptionWithNombre() {
        val jugador = Jugador(
            id = 1, nombre = "",
            apellidos = "García",
            fecha_nacimiento = LocalDate.of(2002, 10, 16),
            fecha_incorporacion = LocalDate.of(2017, 10, 13),
            salario = 1000.0,
            pais = "España",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            isDeleted = false,
            posicion = Posicion.DELANTERO,
            dorsal = 9,
            altura = 1.82,
            peso = 85.0,
            goles = 40,
            partidos_jugados = 100
        )

        val expected = "Integrante no válido: El nombre no puede estar vacío"
        val actual = assertThrows<Exceptions.InvalidoException> { validador.validar(jugador) }

        assertEquals(expected, actual.message)
    }

    @Test
    fun validateThrowsExceptionWithApellidos() {
        val jugador = Jugador(
            id = 1, nombre = "Pepe",
            apellidos = "",
            fecha_nacimiento = LocalDate.of(2002, 10, 16),
            fecha_incorporacion = LocalDate.of(2017, 10, 13),
            salario = 1000.0,
            pais = "España",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            isDeleted = false,
            posicion = Posicion.DELANTERO,
            dorsal = 9,
            altura = 1.82,
            peso = 85.0,
            goles = 40,
            partidos_jugados = 100
        )

        val expected = "Integrante no válido: Los apellidos no pueden estar vacíos"
        val actual = assertThrows<Exceptions.InvalidoException> { validador.validar(jugador) }

        assertEquals(expected, actual.message)
    }

    @Test
    fun validateThrowsExceptionWithFechaNacimiento() {
        val jugador = Jugador(
            id = 1, nombre = "Pepe",
            apellidos = "García",
            fecha_nacimiento = LocalDate.of(10000, 10, 16),
            fecha_incorporacion = LocalDate.of(2017, 10, 13),
            salario = 1000.0,
            pais = "España",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            isDeleted = false,
            posicion = Posicion.DELANTERO,
            dorsal = 9,
            altura = 1.82,
            peso = 85.0,
            goles = 40,
            partidos_jugados = 100
        )

        val expected = "Integrante no válido: La fecha de nacimiento no puede ser posterior a la fecha actual"
        val actual = assertThrows<Exceptions.InvalidoException> { validador.validar(jugador) }

        assertEquals(expected, actual.message)
    }

    @Test
    fun validateThrowsExceptionWithFechaIncorporacion() {
        val jugador = Jugador(
            id = 1, nombre = "Pepe",
            apellidos = "García",
            fecha_nacimiento = LocalDate.of(2002, 10, 16),
            fecha_incorporacion = LocalDate.of(10000, 10, 13),
            salario = 1000.0,
            pais = "España",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            isDeleted = false,
            posicion = Posicion.DELANTERO,
            dorsal = 9,
            altura = 1.82,
            peso = 85.0,
            goles = 40,
            partidos_jugados = 100
        )

        val expected = "Integrante no válido: La fecha de incorporación no puede ser posterior a la fecha actual"
        val actual = assertThrows<Exceptions.InvalidoException> { validador.validar(jugador) }

        assertEquals(expected, actual.message)
    }

    @Test
    fun validateThrowsExceptionWithFechaIncorporacion2() {
        val jugador = Jugador(
            id = 1, nombre = "Pepe",
            apellidos = "García",
            fecha_nacimiento = LocalDate.of(2018, 10, 16),
            fecha_incorporacion = LocalDate.of(2017, 10, 13),
            salario = 1000.0,
            pais = "España",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            isDeleted = false,
            posicion = Posicion.DELANTERO,
            dorsal = 9,
            altura = 1.82,
            peso = 85.0,
            goles = 40,
            partidos_jugados = 100
        )

        val expected = "Integrante no válido: La fecha de incorporación no puede ser anterior a la fecha de nacimiento"
        val actual = assertThrows<Exceptions.InvalidoException> { validador.validar(jugador) }

        assertEquals(expected, actual.message)
    }

    @Test
    fun validateThrowsExceptionWithSalario() {
        val jugador = Jugador(
            id = 1, nombre = "Pepe",
            apellidos = "García",
            fecha_nacimiento = LocalDate.of(2002, 10, 16),
            fecha_incorporacion = LocalDate.of(2017, 10, 13),
            salario = -0.1,
            pais = "España",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            isDeleted = false,
            posicion = Posicion.DELANTERO,
            dorsal = 9,
            altura = 1.82,
            peso = 85.0,
            goles = 40,
            partidos_jugados = 100
        )

        val expected = "Integrante no válido: El salario no puede ser negativo"
        val actual = assertThrows<Exceptions.InvalidoException> { validador.validar(jugador) }

        assertEquals(expected, actual.message)
    }

    @Test
    fun validateThrowsExceptionWithPais() {
        val jugador = Jugador(
            id = 1, nombre = "Pepe",
            apellidos = "García",
            fecha_nacimiento = LocalDate.of(2002, 10, 16),
            fecha_incorporacion = LocalDate.of(2017, 10, 13),
            salario = 1000.0,
            pais = "",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            isDeleted = false,
            posicion = Posicion.DELANTERO,
            dorsal = 9,
            altura = 1.82,
            peso = 85.0,
            goles = 40,
            partidos_jugados = 100
        )

        val expected = "Integrante no válido: El país de origen no puede estar en blanco"
        val actual = assertThrows<Exceptions.InvalidoException> { validador.validar(jugador) }

        assertEquals(expected, actual.message)
    }

    @Test
    fun validateJugadorOk() {
        val jugador = Jugador(
            id = 1, nombre = "Pepe",
            apellidos = "García",
            fecha_nacimiento = LocalDate.of(2002, 10, 16),
            fecha_incorporacion = LocalDate.of(2017, 10, 13),
            salario = 1000.0,
            pais = "España",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            isDeleted = false,
            posicion = Posicion.DELANTERO,
            dorsal = 9,
            altura = 1.82,
            peso = 85.0,
            goles = 40,
            partidos_jugados = 100
        )

        assertDoesNotThrow { validador.validar(jugador) }
    }

    @Test
    fun validateEntrenadorOk() {
        val entrenador = Entrenador(
            id = 1, nombre = "Pepe",
            apellidos = "García",
            fecha_nacimiento = LocalDate.of(2002, 10, 16),
            fecha_incorporacion = LocalDate.of(2017, 10, 13),
            salario = 1000.0,
            pais = "España",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            isDeleted = false,
            especialidad = Especialidad.ENTRENADOR_PRINCIPAL,
        )

        assertDoesNotThrow { validador.validar(entrenador) }
    }

    @Test
    fun validateThrowsExceptionWithDorsal() {
        val jugador = Jugador(
            id = 1, nombre = "Pepe",
            apellidos = "García",
            fecha_nacimiento = LocalDate.of(2002, 10, 16),
            fecha_incorporacion = LocalDate.of(2017, 10, 13),
            salario = 1000.0,
            pais = "España",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            isDeleted = false,
            posicion = Posicion.DELANTERO,
            dorsal = 100,
            altura = 1.82,
            peso = 85.0,
            goles = 40,
            partidos_jugados = 100
        )

        val expected = "Integrante no válido: El dorsal no puede ser menor a 1 ni mayor a 99"
        val actual = assertThrows<Exceptions.InvalidoException> { validador.validar(jugador) }

        assertEquals(expected, actual.message)
    }

    @Test
    fun validateThrowsExceptionWithDorsal2() {
        val jugador = Jugador(
            id = 1, nombre = "Pepe",
            apellidos = "García",
            fecha_nacimiento = LocalDate.of(2002, 10, 16),
            fecha_incorporacion = LocalDate.of(2017, 10, 13),
            salario = 1000.0,
            pais = "España",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            isDeleted = false,
            posicion = Posicion.DELANTERO,
            dorsal = 0,
            altura = 1.82,
            peso = 85.0,
            goles = 40,
            partidos_jugados = 100
        )

        val expected = "Integrante no válido: El dorsal no puede ser menor a 1 ni mayor a 99"
        val actual = assertThrows<Exceptions.InvalidoException> { validador.validar(jugador) }

        assertEquals(expected, actual.message)
    }

    @Test
    fun validateThrowsExceptionWithAltura() {
        val jugador = Jugador(
            id = 1, nombre = "Pepe",
            apellidos = "García",
            fecha_nacimiento = LocalDate.of(2002, 10, 16),
            fecha_incorporacion = LocalDate.of(2017, 10, 13),
            salario = 1000.0,
            pais = "España",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            isDeleted = false,
            posicion = Posicion.DELANTERO,
            dorsal = 9,
            altura = 3.1,
            peso = 85.0,
            goles = 40,
            partidos_jugados = 100
        )

        val expected = "Integrante no válido: La altura no puede ser negativa ni superar los 3 metros"
        val actual = assertThrows<Exceptions.InvalidoException> { validador.validar(jugador) }

        assertEquals(expected, actual.message)
    }

    @Test
    fun validateThrowsExceptionWithAltura2() {
        val jugador = Jugador(
            id = 1, nombre = "Pepe",
            apellidos = "García",
            fecha_nacimiento = LocalDate.of(2002, 10, 16),
            fecha_incorporacion = LocalDate.of(2017, 10, 13),
            salario = 1000.0,
            pais = "España",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            isDeleted = false,
            posicion = Posicion.DELANTERO,
            dorsal = 9,
            altura = -0.1,
            peso = 85.0,
            goles = 40,
            partidos_jugados = 100
        )

        val expected = "Integrante no válido: La altura no puede ser negativa ni superar los 3 metros"
        val actual = assertThrows<Exceptions.InvalidoException> { validador.validar(jugador) }

        assertEquals(expected, actual.message)
    }

    @Test
    fun validateThrowsExceptionWithPeso() {
        val jugador = Jugador(
            id = 1, nombre = "Pepe",
            apellidos = "García",
            fecha_nacimiento = LocalDate.of(2002, 10, 16),
            fecha_incorporacion = LocalDate.of(2017, 10, 13),
            salario = 1000.0,
            pais = "España",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            isDeleted = false,
            posicion = Posicion.DELANTERO,
            dorsal = 9,
            altura = 1.82,
            peso = -0.1,
            goles = 40,
            partidos_jugados = 100
        )

        val expected = "Integrante no válido: El peso no puede ser negativo"
        val actual = assertThrows<Exceptions.InvalidoException> { validador.validar(jugador) }

        assertEquals(expected, actual.message)
    }

    @Test
    fun validateThrowsExceptionWithGoles() {
        val jugador = Jugador(
            id = 1, nombre = "Pepe",
            apellidos = "García",
            fecha_nacimiento = LocalDate.of(2002, 10, 16),
            fecha_incorporacion = LocalDate.of(2017, 10, 13),
            salario = 1000.0,
            pais = "España",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            isDeleted = false,
            posicion = Posicion.DELANTERO,
            dorsal = 9,
            altura = 1.82,
            peso = 85.0,
            goles = -1,
            partidos_jugados = 100
        )

        val expected = "Integrante no válido: El número de goles no puede ser negativo"
        val actual = assertThrows<Exceptions.InvalidoException> { validador.validar(jugador) }

        assertEquals(expected, actual.message)
    }

    @Test
    fun validateThrowsExceptionWithPartidosJugados() {
        val jugador = Jugador(
            id = 1, nombre = "Pepe",
            apellidos = "García",
            fecha_nacimiento = LocalDate.of(2002, 10, 16),
            fecha_incorporacion = LocalDate.of(2017, 10, 13),
            salario = 1000.0,
            pais = "España",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            isDeleted = false,
            posicion = Posicion.DELANTERO,
            dorsal = 9,
            altura = 1.82,
            peso = 85.0,
            goles = 40,
            partidos_jugados = -1
        )

        val expected = "Integrante no válido: El número de partidos jugados no puede ser negativo"
        val actual = assertThrows<Exceptions.InvalidoException> { validador.validar(jugador) }

        assertEquals(expected, actual.message)
    }


}