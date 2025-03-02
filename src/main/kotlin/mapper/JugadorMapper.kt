package org.example.mapper

import org.example.Posicion
import org.example.dto.JugadorDTO
import org.example.models.Jugador
import java.time.LocalDate
import java.time.LocalDateTime

fun Jugador.toDto (): JugadorDTO {
        return JugadorDTO(
            id = id,
            nombre = nombre,
            apellidos = apellidos,
            fecha_nacimiento = fecha_nacimiento.toString(),
            fecha_incorporacion = fecha_incorporacion.toString(),
            salario = salario,
            pais = pais,
            posicion = posicion.toString(),
            dorsal = dorsal,
            altura = altura,
            peso = peso,
            goles = goles,
            partidos_jugados = partidos_jugados
        )
    }

fun JugadorDTO.toModel(): Jugador {
        return Jugador(
            id = id,
            nombre = nombre,
            apellidos = apellidos,
            fecha_nacimiento = LocalDate.parse(fecha_nacimiento),
            fecha_incorporacion = LocalDate.parse(fecha_incorporacion),
            salario = salario,
            pais = pais,
            posicion = Posicion.valueOf(posicion),
            dorsal = dorsal,
            altura = altura,
            peso = peso,
            goles = goles,
            partidos_jugados = partidos_jugados
        )
}
