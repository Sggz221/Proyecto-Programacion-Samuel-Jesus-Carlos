package org.example.validator

import org.example.exceptions.Exceptions
import org.example.models.Integrante
import org.example.models.Jugador
import org.lighthousegames.logging.logging
import java.time.LocalDate

/**
 * Clase que valida un [Integrante]
 */
class IntegranteValidator {
    private var logger = logging()

    /**
     * Valida un integrante en base a una serie de comprobaciones
     * @param integrante [Integrante] Es el integrante a validar
     */
    fun validar (integrante: Integrante) {
        logger.debug { "Validando campos del integrante del equipo" }

        if (integrante.nombre.isBlank()){
            throw Exceptions.InvalidoException("El nombre no puede estar vacío")
        }

        if (integrante.apellidos.isBlank()){
            throw Exceptions.InvalidoException("Los apellidos no pueden estar vacíos")
        }

        if (integrante.fecha_nacimiento > LocalDate.now()){
            throw Exceptions.InvalidoException("La fecha de nacimiento no puede ser posterior a la fecha actual")
        }

        if (integrante.fecha_incorporacion > LocalDate.now()){
            throw Exceptions.InvalidoException("La fecha de incorporación no puede ser posterior a la fecha actual")
        }

        if (integrante.fecha_incorporacion < integrante.fecha_nacimiento) {
            throw Exceptions.InvalidoException("La fecha de incorporación no puede ser anterior a la fecha de nacimiento")
        }

        if (integrante.salario < 0.0){
            throw Exceptions.InvalidoException("El salario no puede ser negativo")
        }

        if (integrante.pais.isBlank()){
            throw Exceptions.InvalidoException("El país de origen no puede estar en blanco")
        }

        if (integrante is Jugador) {
            validarJugador(integrante)
        }
    }

    /**
     * Valida un integrante en base a una serie de comprobaciones
     * @param integrante [Integrante] Es el integrante a validar
     */
    private fun validarJugador (jugador: Jugador) {
        logger.debug { "Validando campos específicos del jugador" }

        if (jugador.dorsal !in 1..99) {
            throw Exceptions.InvalidoException("El dorsal no puede ser menor a 1 ni mayor a 99")
        }

        if (jugador.altura !in 0.0..3.0){
            throw Exceptions.InvalidoException("La altura no puede ser negativa ni superar los 3 metros")
        }

        if (jugador.peso < 0.0) {
            throw Exceptions.InvalidoException("El peso no puede ser negativo")
        }

        if (jugador.goles < 0) {
            throw Exceptions.InvalidoException("El número de goles no puede ser negativo")
        }

        if (jugador.partidos_jugados < 0){
            throw Exceptions.InvalidoException("El número de partidos jugados no puede ser negativo")
        }
    }
}