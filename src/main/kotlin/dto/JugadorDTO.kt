package org.example.dto

import java.time.LocalDateTime

data class JugadorDTO (
    val id: Long,
    val nombre: String,
    val apellidos: String,
    val fecha_nacimiento: String,
    val fecha_incorporacion: String,
    val salario: Double,
    val pais: String,
    val createdAt: String = LocalDateTime.now().toString(),
    val updatedAt: String = LocalDateTime.now().toString(),
    val isDeleted: Boolean = false,
    val posicion: String,
    val dorsal: Int,
    val altura: Double,
    val peso: Double,
    val goles: Int,
    val partidos_jugados: Int
) {
}