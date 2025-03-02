package org.example.mapper

import org.example.dto.JugadorDTO
import org.example.models.CentrocampistaImpl
import org.example.models.Jugador
import org.example.models.interfaces.Centrocampista
import org.example.models.interfaces.Posicion
import java.time.LocalDate
import java.time.LocalDateTime

class JugadorMapper {

    fun Jugador.toDto (): JugadorDTO {
        return JugadorDTO(
            id = id,
            nombre = nombre,
            apellidos = apellidos,
            fecha_nacimiento = fecha_nacimiento.toString(),
            fecha_incorporacion = fecha_incorporacion.toString(),
            salario = salario,
            pais = pais,
            createdAt = createdAt.toString(),
            updatedAt = updatedAt.toString(),
            isDeleted = isDeleted,
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
            createdAt = LocalDateTime.parse(createdAt),
            updatedAt = LocalDateTime.parse(updatedAt),
            isDeleted = isDeleted,
            posicion = posicion,
            dorsal = TODO(),
            altura = TODO(),
            peso = TODO(),
            goles = TODO(),
            partidos_jugados = TODO()
        )
    }
    
}