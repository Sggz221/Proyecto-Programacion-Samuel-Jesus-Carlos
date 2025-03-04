package org.example.extensions

import org.example.models.Entrenador
import org.example.models.Especialidad
import org.example.models.Jugador
import org.example.models.Posicion
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Crea una copia de un objeto de la clase Jugador
 * @return La copia del objeto creado
 */
fun Jugador.copy(
    newId: Long= this.id,
    newNombre: String= this.nombre,
    newApellidos: String = this.apellidos,
    new_fecha_nacimiento: LocalDate = this.fecha_nacimiento,
    new_fecha_incorporacion: LocalDate = this.fecha_incorporacion,
    newSalario: Double = this.salario,
    newPais: String = this.pais,
    newIsDeleted: Boolean = this.isDeleted,
    newPosicion: Posicion = this.posicion,
    newDorsal: Int = this.dorsal,
    newAltura: Double = this.altura,
    newPeso: Double = this.peso,
    newGoles: Int  = this.goles,
    new_partidos_jugados: Int  = this.partidos_jugados,
    timeStamp: LocalDateTime = LocalDateTime.now()
): Jugador {
    return Jugador(
        id = newId,
        nombre = newNombre,
        apellidos = newApellidos,
        fecha_nacimiento = new_fecha_nacimiento,
        fecha_incorporacion = new_fecha_incorporacion,
        salario = newSalario,
        pais = newPais,
        createdAt = timeStamp,
        updatedAt = timeStamp,
        isDeleted = newIsDeleted,
        posicion = newPosicion,
        dorsal = newDorsal,
        altura = newAltura,
        peso = newPeso,
        goles = newGoles,
        partidos_jugados = new_partidos_jugados
    )
}
/**
 * Crea una copia de un objeto de la clase Entrenador
 * @return La copia del objeto creado
 */
fun Entrenador.copy(
    newId: Long= this.id,
    newNombre: String= this.nombre,
    newApellidos: String = this.apellidos,
    new_fecha_nacimiento: LocalDate = this.fecha_nacimiento,
    new_fecha_incorporacion: LocalDate = this.fecha_incorporacion,
    newSalario: Double = this.salario,
    newPais: String = this.pais,
    newIsDeleted: Boolean = this.isDeleted,
    newEspecialidad: Especialidad = this.especialidad,
    timeStamp: LocalDateTime = LocalDateTime.now()
): Entrenador {
    return Entrenador(
        id = newId,
        nombre = newNombre,
        apellidos = newApellidos,
        fecha_nacimiento = new_fecha_nacimiento,
        fecha_incorporacion = new_fecha_incorporacion,
        salario = newSalario,
        pais = newPais,
        createdAt = timeStamp,
        updatedAt = timeStamp,
        isDeleted = newIsDeleted,
        especialidad = newEspecialidad,
    )
}