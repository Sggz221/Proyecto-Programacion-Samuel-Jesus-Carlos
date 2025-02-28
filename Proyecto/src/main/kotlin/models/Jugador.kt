package org.example.models

import java.time.LocalDateTime

class Jugador(
    id: Long,
    nombre: String,
    apellidos: String,
    fecha_nacimiento: String, // Localizar
    fecha_incorporacion: String, //Localizar
    salario: Double,
    pais: String,
    rol: String,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime,
    isDeleted: Boolean,
    val posicion: String, // Arreglar
    val dorsal: Int,
    val altura: Double,
    val peso: Double,
    val goles: Int,
    val partidos_jugados: Int
): Integrante(id = id, nombre = nombre, apellidos = apellidos, fecha_nacimiento = fecha_nacimiento, fecha_incorporacion = fecha_incorporacion, salario = salario, pais = pais, rol = rol,createdAt = createdAt,updatedAt = updatedAt, isDeleted = isDeleted) {
}