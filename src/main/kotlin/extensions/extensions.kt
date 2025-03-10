package org.example.extensions

import org.example.models.Entrenador
import org.example.models.Jugador
import java.time.LocalDateTime

/**
 * Crea una copia de un objeto de la clase Jugador
 * @return La copia del objeto creado
 */
fun Jugador.copy(newId: Long, timeStamp: LocalDateTime = LocalDateTime.now()): Jugador {
    return Jugador(
        id = newId,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fecha_nacimiento = this.fecha_nacimiento,
        fecha_incorporacion = this.fecha_incorporacion,
        salario = this.salario,
        pais = this.pais,
        createdAt = timeStamp,
        updatedAt = timeStamp,
        isDeleted = this.isDeleted,
        posicion = this.posicion,
        dorsal = this.dorsal,
        altura = this.altura,
        peso = this.peso,
        goles = this.goles,
        partidos_jugados = this.partidos_jugados
    )
}
/**
 * Crea una copia de un objeto de la clase Entrenador
 * @return La copia del objeto creado
 */
fun Entrenador.copy(newId: Long, timeStamp: LocalDateTime = LocalDateTime.now()): Entrenador {
    return Entrenador(
        id = newId,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fecha_nacimiento = this.fecha_nacimiento,
        fecha_incorporacion = this.fecha_incorporacion,
        salario = this.salario,
        pais = this.pais,
        createdAt = timeStamp,
        updatedAt = timeStamp,
        isDeleted = this.isDeleted,
        especialidad = this.especialidad
    )
}