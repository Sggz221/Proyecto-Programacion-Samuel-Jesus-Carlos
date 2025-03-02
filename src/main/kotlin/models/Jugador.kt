package org.example.models

import org.example.models.interfaces.Posicion
import java.time.LocalDateTime

class Jugador(
    id: Long,
    nombre: String,
    apellidos: String,
    fecha_nacimiento: String, // Localizar
    fecha_incorporacion: String, //Localizar
    salario: Double,
    pais: String,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime,
    isDeleted: Boolean,
    val posicion: Posicion,
    val dorsal: Int,
    val altura: Double,
    val peso: Double,
    val goles: Int,
    val partidos_jugados: Int
): Integrante(id = id, nombre = nombre, apellidos = apellidos, fecha_nacimiento = fecha_nacimiento, fecha_incorporacion = fecha_incorporacion, salario = salario, pais = pais, createdAt = createdAt,updatedAt = updatedAt, isDeleted = isDeleted) {
}